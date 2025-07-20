package com.pmb.auth.presentation.register.authentication_select_services

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.pmb.auth.R
import com.pmb.auth.presentation.register.authentication_select_services.viewModel.AuthenticationSelectServicesViewEvent
import com.pmb.auth.presentation.register.authentication_select_services.viewModel.AuthenticationSelectServicesViewModel
import com.pmb.ballon.component.AlertComponent
import com.pmb.ballon.component.RoundedCornerCheckboxComponent
import com.pmb.ballon.component.base.AppButton
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppLoading
import com.pmb.ballon.component.base.AppTextButton
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.ballon.component.base.BodyMediumText
import com.pmb.ballon.component.base.BodySmallText
import com.pmb.ballon.component.base.CaptionText
import com.pmb.ballon.component.base.Headline6Text
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.core.utils.toCurrency
import com.pmb.navigation.manager.LocalNavigationManager
import com.pmb.navigation.manager.NavigationManager
import com.pmb.navigation.moduleScreen.RegisterScreens

@Composable
fun AuthenticationSelectServicesScreen(
    viewModel: AuthenticationSelectServicesViewModel
) {
    val navigationManager: NavigationManager = LocalNavigationManager.current
    val viewState by viewModel.viewState.collectAsState()
    var showFeeDetails by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(Unit) {
        viewModel.viewEvent.collect { event ->
            when (event) {
                AuthenticationSelectServicesViewEvent.SelectServicesSucceed -> {
                    navigationManager.navigate(RegisterScreens.RegisterChooseCard)
                }
            }
        }
    }
    viewState.localData?.let { selectServices ->
        if (!showFeeDetails) {
            AppContent(
                modifier = Modifier.padding(horizontal = 16.dp),
                topBar = {
                    AppTopBar(
                        title = stringResource(R.string.select_services),
                        onBack = {
                            navigationManager.navigateBack()
                        }
                    )
                },
                footer = {
                    viewState.totalPrice?.let {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 18.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {

                            BodySmallText(
                                text = stringResource(R.string.services_total, it),
                                color = AppTheme.colorScheme.onBackgroundPrimarySubdued
                            )

                            Spacer(modifier = Modifier.size(5.dp))
                            AppTextButton(
                                modifier = Modifier.weight(1f),
                                title = stringResource(R.string.show_details),
                                onClick = {
                                    showFeeDetails = true
                                })
                        }
                    }
                    Spacer(modifier = Modifier.size(10.dp))
                    AppButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp, top = 16.dp),
                        enable = !viewState.data.isNullOrEmpty(),
                        title = stringResource(R.string._continue),
                        onClick = {
                            navigationManager.navigate(RegisterScreens.RegisterChooseCard)
                        })
                }
            ) {

                Spacer(modifier = Modifier.size(24.dp))
                Headline6Text(
                    text = stringResource(R.string.select_services_title),
                    color = AppTheme.colorScheme.onBackgroundNeutralDefault,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.size(60.dp))
                BodySmallText(
                    text = stringResource(R.string.select_services_description),
                    color = AppTheme.colorScheme.onBackgroundPrimarySubdued,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.size(12.dp))
                selectServices.selectServicesList.forEach { selectServiceObject ->
                    RoundedCornerCheckboxComponent(
                        title = selectServiceObject.title,
                        caption = stringResource(
                            R.string.tax_amount,
                            selectServiceObject.getSeparatedPrice()
                        ),
                        isChecked = selectServiceObject.isChecked.value
                    ) {
                        viewModel.changeSelectServicesFlag(selectServiceObject.id)
                    }
                    Spacer(modifier = Modifier.size(12.dp))
                }
            }
        } else {
            AppContent(
                modifier = Modifier.padding(horizontal = 16.dp),
                backgroundColor = AppTheme.colorScheme.background2Neutral,
                topBar = {
                    AppTopBar(
                        title = stringResource(R.string.fee_details),
                        onBack = {
                            showFeeDetails = false
                        }
                    )
                },
                footer = {
                    AppButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp, top = 16.dp),
                        enable = true,
                        title = stringResource(com.pmb.ballon.R.string.confirm),
                        onClick = {
                            showFeeDetails = false
                        })
                }
            ) {
                viewState.data?.let { feeDetailsEntity ->
                    Spacer(modifier = Modifier.size(20.dp))
                    Column(
                        modifier = Modifier
                            .background(
                                AppTheme.colorScheme.background1Neutral,
                                RoundedCornerShape(12.dp)
                            )
                            .padding(horizontal = 10.dp)
                    ) {
                        feeDetailsEntity.forEachIndexed { index, feeDetail ->
                            Row(
                                modifier = Modifier.heightIn(min = 56.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                CaptionText(
                                    text = feeDetail.desc,
                                    color = AppTheme.colorScheme.onBackgroundNeutralSubdued
                                )
                                Spacer(modifier = Modifier.weight(1f))
                                BodyMediumText(
                                    text = feeDetail.amount.toDouble().toCurrency(),
                                    color = AppTheme.colorScheme.onBackgroundNeutralDefault
                                )
                                Spacer(modifier = Modifier.size(4.dp))
                                CaptionText(
                                    text = stringResource(com.pmb.ballon.R.string.rial),
                                    color = AppTheme.colorScheme.onBackgroundNeutralSubdued
                                )
                            }
                            if (index < feeDetailsEntity.size - 1) {
                                HorizontalDivider(
                                    thickness = 1.dp,
                                    color = AppTheme.colorScheme.strokeNeutral3Devider
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.size(10.dp))
                    Column(
                        modifier = Modifier
                            .background(
                                AppTheme.colorScheme.background1Neutral,
                                RoundedCornerShape(12.dp)
                            )
                            .padding(horizontal = 10.dp)
                    ) {
                        Row(
                            modifier = Modifier.heightIn(min = 56.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            BodyMediumText(
                                text = stringResource(R.string.total),
                                color = AppTheme.colorScheme.foregroundNeutralDefault
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            viewState.totalPrice?.let {
                                BodyMediumText(
                                    text = it,
                                    color = AppTheme.colorScheme.onBackgroundNeutralDefault
                                )
                            }
                            Spacer(modifier = Modifier.size(4.dp))
                            CaptionText(
                                text = stringResource(com.pmb.ballon.R.string.rial),
                                color = AppTheme.colorScheme.onBackgroundNeutralSubdued
                            )
                        }
                    }
                }
            }
        }
    }
    if (viewState.loading) {
        AppLoading()
    }
    if (viewState.alertModelState != null) {
        AlertComponent(viewState.alertModelState!!)
    }
}

