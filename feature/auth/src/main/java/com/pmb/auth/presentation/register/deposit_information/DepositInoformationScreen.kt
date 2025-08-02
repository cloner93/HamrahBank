package com.pmb.auth.presentation.register.deposit_information

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.pmb.auth.R
import com.pmb.auth.presentation.register.RegisterSharedViewState
import com.pmb.auth.presentation.register.deposit_information.viewModel.DepositInformationViewActions
import com.pmb.auth.presentation.register.deposit_information.viewModel.DepositInformationViewEvents
import com.pmb.auth.presentation.register.deposit_information.viewModel.DepositInformationViewModel
import com.pmb.auth.presentation.register.deposit_information.viewModel.DepositInformationViewState
import com.pmb.ballon.component.AlertComponent
import com.pmb.ballon.component.CustomSpinner
import com.pmb.ballon.component.RoundedCornerCheckboxComponent
import com.pmb.ballon.component.base.AppButton
import com.pmb.ballon.component.base.AppClickableReadOnlyTextField
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppLoading
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.ballon.component.base.BodyMediumText
import com.pmb.ballon.component.base.ClickableIcon
import com.pmb.ballon.component.base.IconType
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.core.utils.CollectOnce
import com.pmb.domain.model.openAccount.accountType.Province
import com.pmb.domain.model.openAccount.branchName.Branch
import com.pmb.domain.model.openAccount.cityName.City
import com.pmb.navigation.manager.LocalNavigationManager
import com.pmb.navigation.manager.NavigationManager
import com.pmb.navigation.moduleScreen.RegisterScreens

@Composable
fun DepositInformationScreen(
    viewModel: DepositInformationViewModel,
    sharedViewState: RegisterSharedViewState,
    setProvince: (List<Province>?) -> Unit,
    setCity: (List<City>?) -> Unit,
    updateSharedState: (DepositInformationViewState) -> Unit
) {
    val navigationManager: NavigationManager = LocalNavigationManager.current
    val viewState by viewModel.viewState.collectAsState()
    var city by remember { mutableStateOf(sharedViewState.cityOfDeposit?.cityName ?: "") }
    var province by remember {
        mutableStateOf(
            sharedViewState.provinceOfDeposit?.provinceName ?: ""
        )
    }
    LaunchedEffect(Unit) {
        if (viewState.fetchAccountTypeResponse == null)
            viewModel.handle(
                DepositInformationViewActions.FetchAccountType(
                    sharedViewState.nationalCode ?: "", sharedViewState.mobileNo ?: ""
                )
            )
    }
    var showBottomSheet by remember { mutableStateOf(false) }
    BackHandler {
        if (showBottomSheet) {
            showBottomSheet = false
        } else {
            navigationManager.navigateBack()
        }
    }
    LaunchedEffect(Unit) {
        viewModel.viewEvent.collect { event ->
            when (event) {
                DepositInformationViewEvents.GetCommitmentTextSucceed -> {
                    showBottomSheet = true
                }
            }
        }
    }

    navigationManager.getCurrentScreenFlowData<Branch?>(
        "openingBranch",
        null
    )?.CollectOnce {
        it.takeIf {
            it != null
        }?.also {
            viewModel.handle(DepositInformationViewActions.SetOpeningBranch(it))
        }
    }
    LaunchedEffect(sharedViewState.cityOfDeposit) {
        if (sharedViewState.cityOfDeposit != null) {
            city = sharedViewState.cityOfDeposit?.cityName ?: ""
            viewModel.handle(DepositInformationViewActions.SetCity(sharedViewState.cityOfDeposit!!))
        }
    }
    LaunchedEffect(sharedViewState.provinceOfDeposit) {
        if (sharedViewState.provinceOfDeposit != null) {
            province = sharedViewState.provinceOfDeposit?.provinceName ?: ""
            viewModel.handle(DepositInformationViewActions.SetProvince(sharedViewState.provinceOfDeposit!!))
        }
    }



    if (!showBottomSheet) {
        AppContent(
            modifier = Modifier.padding(horizontal = 16.dp),
            topBar = {
                AppTopBar(
                    title = stringResource(R.string.deposit_information),
                    onBack = {
                        navigationManager.navigateBack()
                    }
                )
            },
            footer = {
                RoundedCornerCheckboxComponent(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                        .clickable {
                            viewModel.handle(DepositInformationViewActions.FetchCommitment)
                        },
                    title = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.Normal,
                                color = AppTheme.colorScheme.foregroundPrimaryDefault,
                            )
                        ) {
                            append(stringResource(R.string.rules))
                        }
                        append(" ")
                        append(stringResource(R.string.rules_read_and_accepted))
                    },
                    isChecked = viewState.isChecked
                ) {
                    if (viewState.isChecked)
                        viewModel.handle(DepositInformationViewActions.SelectRules)
                    else
                        viewModel.handle(DepositInformationViewActions.FetchCommitment)
                }
                Spacer(modifier = Modifier.size(22.dp))
                AppButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, top = 16.dp),
                    enable = !viewState.isLoading && viewState.accType != null && viewState.province != null && viewState.isChecked && viewState.city != null && viewState.branch != null,
                    title = stringResource(R.string._continue),
                    onClick = {
                        updateSharedState(viewState)
                        navigationManager.navigate(RegisterScreens.Signature)
                    })
            }) {
//            viewState.depositInformation.takeIf { it?.isSuccess == true }?.let {
            Spacer(modifier = Modifier.size(24.dp))
            CustomSpinner(
                modifier = Modifier
                    .fillMaxWidth(),
                options = viewState.fetchAccountTypeResponse?.accTypeList?.map { it.accountTypeDesc },
                labelString = "نوع حساب",
                displayText = viewState.accType?.accountTypeDesc ?: "",
                isEnabled = viewState.fetchAccountTypeResponse?.accTypeList?.isNotEmpty() == true
            ) { type ->
                val accType =
                    viewState.fetchAccountTypeResponse?.accTypeList?.find { it.accountTypeDesc == type }
                accType?.let {
                    viewModel.handle(DepositInformationViewActions.SetAccountType(it))
                }
            }
            Spacer(modifier = Modifier.size(12.dp))
            AppClickableReadOnlyTextField(
                onClick = {
                    city = ""
                    viewModel.handle(DepositInformationViewActions.SetOpeningBranch(null))
                    viewModel.handle(DepositInformationViewActions.SetCity(null))
                    setProvince(viewState.fetchAccountTypeResponse?.provinceList)
                    navigationManager.navigate(RegisterScreens.SelectProvincePlace)
                },
                value = province,
                label = "استان شعبه مورد نظر",
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = "down Icon",
                        tint = AppTheme.colorScheme.onBackgroundNeutralDefault
                    )
                },
            )
            Spacer(modifier = Modifier.size(12.dp))

            AppClickableReadOnlyTextField(
                onClick = {
                    viewModel.handle(DepositInformationViewActions.SetOpeningBranch(null))
                    setCity(viewState.cityList)
                    navigationManager.navigate(RegisterScreens.SelectCityPlace)
                },
                value = city,
                label = "شهر شعبه مورد نظر",
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = "down Icon",
                        tint = AppTheme.colorScheme.onBackgroundNeutralDefault
                    )
                },
            )
            Spacer(modifier = Modifier.size(12.dp))

            AppClickableReadOnlyTextField(
                value = viewState.branch?.branchName ?: "",
                onClick = {
                    if (viewState.cityList != null)
                        navigationManager.navigateWithString(
                            RegisterScreens.SearchOpeningBranch.createRoute(
                                viewState.city?.cityCode ?: -1,
                                viewState.city?.cityName ?: "",
                                viewState.city?.provinceCode ?: -1,
                                viewState.province?.provinceName ?: ""
                            )
                        )
                },
                label = "شعبه افتتاح کننده",
                enabled = viewState.cityList != null,
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = "down Icon",
                        tint = AppTheme.colorScheme.onBackgroundNeutralDefault
                    )
                },

                )
        }
        if (viewState.isLoading) {
            AppLoading()
        }
        if (viewState.alertModelState != null) {
            AlertComponent(viewState.alertModelState!!)
        }
    } else {
        AppContent(
            modifier = Modifier.padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            topBar = {
                AppTopBar(
                    title = stringResource(R.string.rules), startIcon = ClickableIcon(
                        icon = IconType.ImageVector(Icons.Default.Close),
                        onClick = {
                            showBottomSheet = false
                        })
                )
            },
        ) {
            BodyMediumText(
                textAlign = TextAlign.Center,
                text = viewState.commitmentText ?: "",
                color = AppTheme.colorScheme.onBackgroundNeutralDefault
            )
            AppButton(
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .fillMaxWidth(),
                title = stringResource(R.string.accept),
                onClick = {
                    showBottomSheet = false
                    viewModel.handle(DepositInformationViewActions.AcceptRules)
                })
        }

    }
}



