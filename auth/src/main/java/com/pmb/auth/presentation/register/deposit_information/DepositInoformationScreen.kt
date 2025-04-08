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
import com.pmb.auth.domain.register.search_opening_branch.entity.OpeningBranch
import com.pmb.auth.presentation.AuthScreens
import com.pmb.auth.presentation.component.RoundedCornerCheckboxComponent
import com.pmb.auth.presentation.register.deposit_information.viewModel.DepositInformationViewActions
import com.pmb.auth.presentation.register.deposit_information.viewModel.DepositInformationViewEvents
import com.pmb.auth.presentation.register.deposit_information.viewModel.DepositInformationViewModel
import com.pmb.ballon.component.AlertComponent
import com.pmb.ballon.component.CustomSpinner
import com.pmb.ballon.component.base.AppButton
import com.pmb.ballon.component.base.AppClickableReadOnlyTextField
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppLoading
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.ballon.component.base.BodyMediumText
import com.pmb.ballon.component.base.ClickableIcon
import com.pmb.ballon.component.base.IconType
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.core.presentation.NavigationManager
import com.pmb.core.utils.CollectAsEffect

@Composable
fun DepositInformationScreen(
    navigationManager: NavigationManager,
    viewModel: DepositInformationViewModel,
) {
    val viewState by viewModel.viewState.collectAsState()
    var showBottomSheet by remember { mutableStateOf(false) }
    BackHandler() {
        if (showBottomSheet) {
            showBottomSheet = false
        } else {
            navigationManager.navigateBack()
        }
    }
    LaunchedEffect(Unit) {
        viewModel.viewEvent.collect { event ->
            when (event) {
                DepositInformationViewEvents.SendDepositInformationSucceeded -> {
                    navigationManager.navigate(AuthScreens.Signature)
                }
            }
        }
    }

    navigationManager.getCurrentScreenFlowData<OpeningBranch?>(
        "openingBranch",
        null
    )?.CollectAsEffect {
        it.takeIf {
            it != null
        }?.also {
            viewModel.handle(DepositInformationViewActions.SetOpeningBranch(it))
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
                            showBottomSheet = true
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
                    showBottomSheet = true
                }
                Spacer(modifier = Modifier.size(22.dp))
                AppButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, top = 16.dp),
                    enable = !viewState.isLoading && viewState.depositInformation != null && viewState.branchCity != null && viewState.isChecked,
                    title = stringResource(R.string._continue),
                    onClick = {
                        viewState.sendDepositInformationParams?.let {
                            viewModel.handle(
                                DepositInformationViewActions.SendDepositInformation(
                                    it
                                )
                            )
                        }
                    })
            }) {
            viewState.depositInformation.takeIf { it?.isSuccess == true }?.let {
                Spacer(modifier = Modifier.size(24.dp))
                CustomSpinner(
                    modifier = Modifier
                        .fillMaxWidth(),
                    options = viewState.depositInformation?.depositType?.map { it.type },
                    labelString = "نوع حساب",
                    displayText = viewState.depositInformation?.depositType?.findLast {
                        it.id == (viewState.sendDepositInformationParams?.depositType ?: -1)
                    }?.type ?: "",
                    isEnabled = viewState.depositInformation?.depositType?.isNotEmpty() == true
                ) { type ->
                    viewState.depositInformation?.depositType?.findLast { it.type == type }?.id?.let {
                        viewModel.handle(
                            DepositInformationViewActions.DepositType(
                                it
                            )
                        )
                    }
                }
                Spacer(modifier = Modifier.size(12.dp))
                CustomSpinner(
                    modifier = Modifier
                        .fillMaxWidth(),
                    options = viewState.depositInformation?.branchProvince?.map { it.province },
                    labelString = "استان شعبه مورد نظر",
                    displayText = viewState.depositInformation?.branchProvince?.findLast {
                        it.id == (viewState.sendDepositInformationParams?.branchProvince ?: -1)
                    }?.province ?: "",
                    isEnabled = viewState.depositInformation?.branchProvince?.isNotEmpty() == true
                ) { type ->
                    viewState.depositInformation?.branchProvince?.findLast { it.province == type }?.id?.let {
                        viewModel.handle(
                            DepositInformationViewActions.GetBranchCity(
                                it
                            )
                        )
                    }
                }
                Spacer(modifier = Modifier.size(12.dp))
                CustomSpinner(
                    modifier = Modifier
                        .fillMaxWidth(),
                    options = viewState.branchCity?.branchCity?.map { it.city },
                    labelString = "شهر شعبه مورد نظر",
                    displayText = viewState.branchCity?.branchCity?.findLast {
                        it.id == (viewState.sendDepositInformationParams?.branchCity ?: -1)
                    }?.city ?: "",
                    isEnabled = viewState.branchCity?.branchCity?.isNotEmpty() == true
                ) { type ->
                    viewState.branchCity?.branchCity?.findLast { it.city == type }?.id?.let {
                        viewModel.handle(
                            DepositInformationViewActions.SetCityId(
                                it
                            )
                        )
                    }
                }
                Spacer(modifier = Modifier.size(12.dp))

                AppClickableReadOnlyTextField(
                    value = viewState.openedBranch?.openingBranch ?: "",
                    onClick = {
                        if (viewState.sendDepositInformationParams?.branchCity != null)
                            navigationManager.navigateWithString(
                                AuthScreens.SearchOpeningBranch.createRoute(
                                    viewState.sendDepositInformationParams?.branchCity ?: -1,
                                    viewState.branchCity?.branchCity?.findLast {
                                        it.id == (viewState.sendDepositInformationParams?.branchCity
                                            ?: -1)
                                    }?.city ?: "",
                                    viewState.depositInformation?.branchProvince?.findLast {
                                        it.id == (viewState.sendDepositInformationParams?.branchProvince
                                            ?: -1)
                                    }?.province ?: ""
                                )
                            )
                    },
                    label = "شعبه افتتاح کننده",
                    enabled = viewState.sendDepositInformationParams?.branchCity != null,
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowDown,
                            contentDescription = "down Icon",
                        )
                    },

                    )


            }
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
            }, footer = {
                AppButton(
                    modifier = Modifier.fillMaxWidth(),
                    title = stringResource(R.string.accept),
                    onClick = {
                        showBottomSheet = false
                        viewModel.handle(DepositInformationViewActions.SelectRules)
                    })
            }) {
            BodyMediumText(
                textAlign = TextAlign.Center,
                text = stringResource(R.string.usage_role_desc),
                color = AppTheme.colorScheme.onBackgroundNeutralDefault
            )
        }

    }
}



