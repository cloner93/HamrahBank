package com.pmb.auth.presentation.register.check_postal_code

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.pmb.auth.R
import com.pmb.auth.presentation.register.check_postal_code.viewModel.CheckPostalCodeViewActions
import com.pmb.auth.presentation.register.check_postal_code.viewModel.CheckPostalCodeViewEvents
import com.pmb.auth.presentation.register.check_postal_code.viewModel.CheckPostalCodeViewModel
import com.pmb.ballon.component.AlertComponent
import com.pmb.ballon.component.base.AppButton
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppLoading
import com.pmb.ballon.component.base.AppNumberTextField
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.ballon.component.base.BodyMediumText
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.navigation.manager.LocalNavigationManager
import com.pmb.navigation.manager.NavigationManager
import com.pmb.navigation.moduleScreen.RegisterScreens

@Composable
fun CheckPostalCodeScreen(
    viewModel: CheckPostalCodeViewModel,
    updateState:(String,String)-> Unit
) {
    val navigationManager: NavigationManager = LocalNavigationManager.current
    val viewState by viewModel.viewState.collectAsState()
    var postalCode by remember {
        mutableStateOf("")
    }
    var address by remember {
        mutableStateOf("")
    }
    LaunchedEffect(Unit) {
        viewModel.viewEvent.collect { event ->
            when (event) {
                CheckPostalCodeViewEvents.CheckAddressSucceed -> {
                    navigationManager.navigate(RegisterScreens.DepositInformation)
                }

                is CheckPostalCodeViewEvents.CheckPostalCode -> {
                    address = event.address
                }
            }
        }
    }
    AppContent(
        modifier = Modifier.padding(horizontal = 16.dp),
        topBar = {
            AppTopBar(
                title = stringResource(R.string.address),
                onBack = {
                    navigationManager.navigateBack()
                }
            )
        },
        footer = {
            AppButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp),
                enable = !viewState.isLoading && address.isNotEmpty() && postalCode.isNotEmpty(),
                title = stringResource(R.string._continue),
                onClick = {
                    updateState(postalCode, address)
                    viewModel.handle(
                        CheckPostalCodeViewActions.CheckAddress(postalCode, address)
                    )
                })
        }
    ) {
        Spacer(modifier = Modifier.size(24.dp))
        BodyMediumText(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.card_sending_info),
            textAlign = TextAlign.Center,
            color = AppTheme.colorScheme.onBackgroundPrimarySubdued
        )
        Spacer(modifier = Modifier.size(24.dp))
        AppNumberTextField(
            modifier = Modifier.fillMaxWidth(),
            value = postalCode,
            label = stringResource(R.string.postal_code),
            onValueChange = {
                if (postalCode.length <= 10) postalCode = it
            },
            trailingIcon = {
                AppButton(
                    modifier = Modifier.padding(end = 6.dp),
                    title = stringResource(R.string.inquiry),
                    enable = postalCode.isNotEmpty() && postalCode.length == 10
                ) {
                    viewModel.handle(CheckPostalCodeViewActions.CheckPostalCode(postalCode))
                }

            }
        )
        Spacer(modifier = Modifier.size(24.dp))
        BodyMediumText(
            modifier = Modifier.fillMaxWidth(),
            text = address,
            textAlign = TextAlign.Center,
            color = AppTheme.colorScheme.onBackgroundPrimaryActive
        )
    }
    if (viewState.isLoading) {
        AppLoading()
    }
    if (viewState.alertModelState != null) {
        AlertComponent(viewState.alertModelState!!)
    }
}

