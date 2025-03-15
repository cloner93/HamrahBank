package com.pmb.auth.presentation.register.deposit_information

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
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
import com.pmb.ballon.component.base.AppBaseTextField
import com.pmb.ballon.component.base.AppButton
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppLoading
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.core.presentation.NavigationManager
import com.pmb.core.utils.CollectAsEffect

@Composable
fun DepositInformationScreen(
    navigationManager: NavigationManager,
    viewModel: DepositInformationViewModel,
) {
    val viewState by viewModel.viewState.collectAsState()
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
    AppContent(modifier = Modifier.padding(horizontal = 16.dp),
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
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 24.dp),
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
                viewModel.handle(DepositInformationViewActions.SelectRules)
            }
            Spacer(modifier = Modifier.size(22.dp))
            AppButton(modifier = Modifier
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
            Box(modifier = Modifier
                .fillMaxWidth()
                .clickable(true) {

                    navigationManager.navigateWithString(
                        AuthScreens.SearchOpeningBranch.createRoute(
                            viewState.sendDepositInformationParams?.branchCity ?: -1,
                            viewState.branchCity?.branchCity?.findLast {
                                it.id == (viewState.sendDepositInformationParams?.branchCity ?: -1)
                            }?.city ?: "",
                            viewState.depositInformation?.branchProvince?.findLast {
                                it.id == (viewState.sendDepositInformationParams?.branchProvince
                                    ?: -1)
                            }?.province ?: ""
                        )
                    )

                }) {
                AppBaseTextField(
                    value = viewState.openedBranch?.openingBranch ?: "",
                    onValueChange = {},
                    readOnly = true,
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
    }
    if (viewState.isLoading) {
        AppLoading()
    }
    if (viewState.alertModelState != null) {
        AlertComponent(viewState.alertModelState!!)
    }
}



