package com.pmb.account.presentation.issueCard.selectAddress

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MenuAnchorType
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.pmb.account.presentation.issueCard.AddressOption
import com.pmb.account.presentation.issueCard.IssueCardSharedState
import com.pmb.account.presentation.issueCard.selectAddress.viewModel.SelectAddressViewActions
import com.pmb.account.presentation.issueCard.selectAddress.viewModel.SelectAddressViewEvents
import com.pmb.account.presentation.issueCard.selectAddress.viewModel.SelectAddressViewModel
import com.pmb.ballon.component.AlertComponent
import com.pmb.ballon.component.base.AppBaseTextField
import com.pmb.ballon.component.base.AppButton
import com.pmb.ballon.component.base.AppClickableReadOnlyTextField
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppLoading
import com.pmb.ballon.component.base.AppNumberTextField
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.ballon.component.base.BodyMediumText
import com.pmb.ballon.component.base.CaptionText
import com.pmb.ballon.component.base.ClickableIcon
import com.pmb.ballon.component.base.IconType
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.domain.model.card.City
import com.pmb.domain.model.openAccount.accountType.Province
import com.pmb.navigation.manager.LocalNavigationManager
import com.pmb.navigation.moduleScreen.AccountScreens

@Composable
fun SelectAddressScreen(
    viewmodel: SelectAddressViewModel,
    sharedState: IssueCardSharedState,
    onUpdateProvinceList: (List<Province>) -> Unit,
    onUpdateCityList: (List<City>) -> Unit,
    onUpdateAddress: (AddressType, String?, String?) -> Unit,
) {
    val viewState by viewmodel.viewState.collectAsState()
    val navigationManager = LocalNavigationManager.current

    LaunchedEffect(Unit) {
        viewmodel.viewEvent.collect { event ->
            when (event) {
                SelectAddressViewEvents.NavigateBack -> navigationManager.navigateBack()
                is SelectAddressViewEvents.UpdateProvinceList -> onUpdateProvinceList(event.provinceList)
                is SelectAddressViewEvents.UpdateCityList -> {
                    onUpdateCityList(event.cityList)
                    navigationManager.navigate(AccountScreens.IssueCard.SelectCityPlaceScreen)
                }
            }
        }
    }

    AppContent(
        modifier = Modifier.padding(horizontal = 16.dp),
        backgroundColor = AppTheme.colorScheme.background1Neutral,
        footer = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val selectedStateAndCity =
                    sharedState.provinceOfDeposit != null && sharedState.cityOfDeposit != null
                val bool =
                    if (selectedStateAndCity && viewState.addressType == AddressType.OLD_ADDRESS) true
                    else if (selectedStateAndCity && viewState.addressType == AddressType.NEW_ADDRESS && (!viewState.postalCode.isNullOrEmpty() && viewState.postalCode?.length == 10 && !viewState.address.isNullOrEmpty())) true
                    else false

                AppButton(
                    modifier = Modifier.fillMaxWidth(),
                    title = "تایید و ادامه",
                    enable = bool,
                    onClick = {
                        onUpdateAddress(
                            viewState.addressType,
                            viewState.address,
                            viewState.postalCode
                        )
                        navigationManager.navigate(AccountScreens.IssueCard.SelectCardShemaScreen)
                    })
            }
        },
        topBar = {
            AppTopBar(
                title = "صدور کارت برای سپرده",
                startIcon = ClickableIcon(
                    icon = IconType.ImageVector(Icons.Default.ArrowForward),
                    onClick = {
                        navigationManager.navigateBack()
                    })
            )
        }) {
        Spacer(modifier = Modifier.height(24.dp))
        AppClickableReadOnlyTextField(
            onClick = {
                navigationManager.navigate(AccountScreens.IssueCard.SelectProvincePlaceScreen)
            },
            value = sharedState.provinceOfDeposit?.provinceName ?: "",
            label = "استان",
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = "down Icon",
                    tint = AppTheme.colorScheme.onBackgroundNeutralDefault
                )
            },
        )

        if (sharedState.provinceOfDeposit != null) {
            Spacer(modifier = Modifier.height(24.dp))
            AppClickableReadOnlyTextField(
                onClick = {
                    if (sharedState.cityList.isNullOrEmpty())
                        viewmodel.handle(SelectAddressViewActions.ChangeCity(sharedState.provinceOfDeposit))
                    else
                        navigationManager.navigate(AccountScreens.IssueCard.SelectCityPlaceScreen)
                },
                value = sharedState.cityOfDeposit?.cityName ?: "",
                label = "شهر",
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = "down Icon",
                        tint = AppTheme.colorScheme.onBackgroundNeutralDefault
                    )
                },
            )
        }
        if (sharedState.cityOfDeposit != null) {
            Spacer(modifier = Modifier.height(24.dp))
            CustomSpinner(
                modifier = Modifier.fillMaxWidth(),
                options = sharedState.userAddress,
                labelString = "انتخاب آدرس",
                displayText = viewState.addressType.title,
                isEnabled = true
            ) { selectedOption ->
                if (!selectedOption.isHeader) {
                    viewmodel.handle(
                        SelectAddressViewActions.ChangeAddressType(
                            AddressType.OLD_ADDRESS,
                            selectedOption.text
                        )
                    )
                } else {
                    viewmodel.handle(
                        SelectAddressViewActions.ChangeAddressType(
                            AddressType.NEW_ADDRESS,
                            null
                        )
                    )
                }
            }

            when (viewState.addressType) {
                AddressType.OLD_ADDRESS -> {

                    Spacer(Modifier.height(32.dp))
                    BodyMediumText(
                        modifier = Modifier.fillMaxWidth(),
                        text = viewState.address ?: "",
                        textAlign = TextAlign.Center,
                        color = AppTheme.colorScheme.onBackgroundNeutralDefault
                    )
                }

                AddressType.NEW_ADDRESS -> {
                    Spacer(Modifier.height(24.dp))
                    AppNumberTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = viewState.postalCode ?: "",
                        label = "کد پستی",
                        isError = !viewState.postalCodeError.isNullOrEmpty(),
                        errorText = viewState.postalCodeError,
                        onValueChange = {
                            if (it.length <= 10)
                                viewmodel.handle(SelectAddressViewActions.ChangePostalCode(it))
                        },
                        trailingIcon = {
                            AppButton(
                                modifier = Modifier.padding(end = 6.dp),
                                title = "استعلام",
                                enable = !viewState.postalCode.isNullOrEmpty() && viewState.postalCode?.length == 10
                            ) {
                                viewmodel.handle(SelectAddressViewActions.InquiryPostCode)
                            }
                        }
                    )

                    Spacer(Modifier.height(32.dp))
                    BodyMediumText(
                        modifier = Modifier.fillMaxWidth(),
                        text = viewState.address ?: "",
                        textAlign = TextAlign.Center,
                        color = AppTheme.colorScheme.onBackgroundNeutralCTA
                    )
                }

                AddressType.UNSPECIFIED -> {}
            }
        }

    }

    if (viewState.isLoading) {
        AppLoading()
    }

    if (viewState.alertModelState != null) {
        AlertComponent(viewState.alertModelState!!)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CustomSpinner(
    modifier: Modifier = Modifier,
    options: List<AddressOption>,
    labelString: String,
    displayText: String,
    isEnabled: Boolean = false,
    onOptionSelected: (AddressOption) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            if (isEnabled) expanded = !expanded
        }) {
        AppBaseTextField(
            value = displayText,
            onValueChange = {},
            label = labelString,
            enabled = isEnabled,
            hideCursor = true,
            trailingIcon = {
                Icon(
                    imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                    contentDescription = "Dropdown Icon",
                    tint = AppTheme.colorScheme.onBackgroundNeutralDefault
                )
            },
            modifier = modifier
                .menuAnchor(MenuAnchorType.PrimaryNotEditable, true)
                .clickable {
                    if (isEnabled) expanded = !expanded
                })

        ExposedDropdownMenu(
            modifier = Modifier.heightIn(max = 300.dp),
            expanded = expanded,
            containerColor = AppTheme.colorScheme.background1Neutral,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = {
                        if (option.isHeader)
                            BodyMediumText(
                                modifier = Modifier.padding(bottom = 6.dp),
                                text = option.text,
                                color = AppTheme.colorScheme.foregroundNeutralDefault
                            )
                        else
                            CaptionText(
                                modifier = Modifier.padding(bottom = 6.dp),
                                text = option.text,
                                color = AppTheme.colorScheme.onBackgroundNeutralSubdued
                            )
                    },
                    onClick = {
                        if (option.isClickable) {
                            onOptionSelected(option)
                            expanded = false
                        }
                    },
                    enabled = option.isClickable,
                    modifier = Modifier.then(
                        if (!option.isClickable) {
                            Modifier.background(Color.Transparent)
                        } else {
                            Modifier
                        }
                    )
                )
            }
        }
    }
}

enum class AddressType(val title: String) {
    OLD_ADDRESS("آدرس ثبت شده قبلی"),
    NEW_ADDRESS("آدرس جدید"),
    UNSPECIFIED("")
}