package com.pmb.auth.presentation.first_login

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.pmb.auth.presentation.first_login.viewModel.FirsLoginViewEvents
import com.pmb.auth.presentation.first_login.viewModel.FirstLoginViewActions
import com.pmb.auth.presentation.first_login.viewModel.FirstLoginViewModel
import com.pmb.ballon.R
import com.pmb.ballon.component.AlertComponent
import com.pmb.ballon.component.base.AppButton
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppLoading
import com.pmb.ballon.component.base.AppMobileTextField
import com.pmb.ballon.component.base.AppOutlineButton
import com.pmb.ballon.component.base.AppSingleTextField
import com.pmb.ballon.component.base.AppTextButton
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.ballon.component.text_field.AppPasswordTextField
import com.pmb.ballon.models.AccountSampleModel
import com.pmb.navigation.manager.LocalNavigationManager
import com.pmb.navigation.manager.NavigationManager
import com.pmb.navigation.moduleScreen.AuthScreens

@Composable
fun FirstLoginScreen(viewModel: FirstLoginViewModel) {
    val navigationManager: NavigationManager = LocalNavigationManager.current
    val viewState by viewModel.viewState.collectAsState()

    // Handle one-time events such as navigation or showing toasts
    LaunchedEffect(Unit) {
        viewModel.viewEvent.collect { event ->
            when (event) {
                FirsLoginViewEvents.FirstLoginStepSucceed -> {
                    navigationManager.navigate(AuthScreens.FirstLoginConfirm)
                }
            }
        }
    }

    AppContent(modifier = Modifier.padding(horizontal = 24.dp), topBar = {
        AppTopBar(
            title = stringResource(com.pmb.auth.R.string.login_to_hamrah_bank),
            onBack = { navigationManager.navigateBack() })
    }, horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally, footer = {
        AppOutlineButton(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .fillMaxWidth(),
            title = stringResource(com.pmb.auth.R.string.hamrah_bank_activate),
            onClick = {
                navigationManager.navigate(AuthScreens.Activation)
            })
    }) {
        Spacer(modifier = Modifier.size(24.dp))
        Image(
            modifier = Modifier
                .size(56.dp)
                .clickable {
                    AccountSampleModel().let {
                        viewModel.handle(FirstLoginViewActions.UpdatePhoneNumber(it.mobileNumber))
                        viewModel.handle(FirstLoginViewActions.UpdateUsername(it.userName))
                        viewModel.handle(FirstLoginViewActions.UpdatePassword(it.passWord))
                    }
                },
            painter = painterResource(R.drawable.img_mellat_logo),
            contentDescription = "mellat logo"
        )

        Spacer(modifier = Modifier.size(32.dp))

        AppMobileTextField(
            value = viewState.phoneNumber,
            label = stringResource(com.pmb.auth.R.string.phone_number),
            onValueChange = {
                viewModel.handle(FirstLoginViewActions.UpdatePhoneNumber(it))
            })

        Spacer(modifier = Modifier.size(24.dp))


        AppSingleTextField(
            modifier = Modifier.fillMaxWidth(),
            value = viewState.username,
            label = stringResource(com.pmb.auth.R.string.username),
            onValueChange = {
                viewModel.handle(FirstLoginViewActions.UpdateUsername(it))
            },
        )

        Spacer(modifier = Modifier.size(24.dp))

        AppPasswordTextField(
            modifier = Modifier.fillMaxWidth(),
            value = viewState.password,
            label = stringResource(com.pmb.auth.R.string.password),
            onValueChange = {
                viewModel.handle(FirstLoginViewActions.UpdatePassword(it))
            })

        Spacer(modifier = Modifier.size(32.dp))

        AppButton(
            modifier = Modifier.fillMaxWidth(),
            title = stringResource(com.pmb.auth.R.string._continue),
            enable = viewState.enableButton,
            onClick = {
                viewModel.handle(FirstLoginViewActions.FirstLoginStepConfirm)
            })

        Spacer(modifier = Modifier.size(8.dp))

        AppTextButton(
            modifier = Modifier.fillMaxWidth(),
            title = stringResource(com.pmb.auth.R.string.forget_password),
            onClick = {
                navigationManager.navigate(AuthScreens.ForgetPassword)
            })
    }
    if (viewState.loading) AppLoading()
    viewState.alertModelState?.let { AlertComponent(it) }
}