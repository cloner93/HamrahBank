package com.pmb.auth.presentation.ekyc.authentication_select_services

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.pmb.auth.R
import com.pmb.auth.presentation.component.RoundedCornerCheckboxComponent
import com.pmb.auth.presentation.ekyc.authentication_select_services.viewModel.AuthenticationSelectServicesViewActions
import com.pmb.auth.presentation.ekyc.authentication_select_services.viewModel.AuthenticationSelectServicesViewEvent
import com.pmb.auth.presentation.ekyc.authentication_select_services.viewModel.AuthenticationSelectServicesViewModel
import com.pmb.ballon.component.AlertComponent
import com.pmb.ballon.component.base.AppButton
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppLoading
import com.pmb.ballon.component.base.AppTextButton
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.ballon.component.base.BodySmallText
import com.pmb.ballon.component.base.Headline6Text
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.navigation.manager.LocalNavigationManager
import com.pmb.navigation.manager.NavigationManager
import com.pmb.navigation.moduleScreen.AuthScreens

@Composable
fun AuthenticationSelectServicesScreen(
    viewModel: AuthenticationSelectServicesViewModel
) {
    val navigationManager: NavigationManager = LocalNavigationManager.current
    val viewState by viewModel.viewState.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.viewEvent.collect { event ->
            when (event) {
                AuthenticationSelectServicesViewEvent.SelectServicesSucceed -> {
                    navigationManager.navigate(AuthScreens.Authentication)
                }
            }
        }
    }
    viewState.data?.let { selectServices ->
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
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 18.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    BodySmallText(
                        text = stringResource(R.string.services_total, viewModel.getTotalPrice()),
                        color = AppTheme.colorScheme.onBackgroundPrimarySubdued
                    )
                    Spacer(modifier = Modifier.size(5.dp))
                    AppTextButton(
                        modifier = Modifier.weight(1f),
                        title = stringResource(R.string.show_details),
                        onClick = {
                            navigationManager.navigate(AuthScreens.FeeDetails)
                        })
                }
                Spacer(modifier = Modifier.size(10.dp))
                AppButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, top = 16.dp),
                    enable = !viewModel.hasIsCheckedFlag().isNullOrEmpty(),
                    title = stringResource(R.string._continue),
                    onClick = {
                        viewModel.getIsCheckedFlagIds()?.let {
                            viewModel.handle(
                                AuthenticationSelectServicesViewActions.ConfirmAuthenticationSelectedServices(
                                    it
                                )
                            )
                        }
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
    }
    if (viewState.loading) {
        AppLoading()
    }
    if (viewState.alertModelState != null) {
        AlertComponent(viewState.alertModelState!!)
    }
}

