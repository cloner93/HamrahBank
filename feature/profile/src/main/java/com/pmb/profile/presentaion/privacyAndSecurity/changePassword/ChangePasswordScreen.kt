package com.pmb.profile.presentaion.privacyAndSecurity.changePassword

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pmb.ballon.component.AlertComponent
import com.pmb.ballon.component.annotation.AppPreview
import com.pmb.ballon.component.base.AppButton
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppLoading
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.ballon.component.text_field.AppPasswordTextField
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.ballon.ui.theme.HamrahBankTheme
import com.pmb.navigation.manager.LocalNavigationManager
import com.pmb.profile.presentaion.privacyAndSecurity.changePassword.viewmodel.ChangePasswordViewActions
import com.pmb.profile.presentaion.privacyAndSecurity.changePassword.viewmodel.ChangePasswordViewEvents
import com.pmb.profile.presentaion.privacyAndSecurity.changePassword.viewmodel.ChangePasswordViewModel

@Composable
fun ChangePasswordScreen() {
    val viewModel = hiltViewModel<ChangePasswordViewModel>()
    val viewState by viewModel.viewState.collectAsState()

    val navigationManager = LocalNavigationManager.current

    LaunchedEffect(Unit) {
        viewModel.viewEvent.collect { event ->
            when (event) {
                is ChangePasswordViewEvents.ShowError -> {

                }

                ChangePasswordViewEvents.NavigateBack -> {
                    navigationManager.navigateBack()
                }
            }
        }
    }

    AppContent(
        modifier = Modifier
            .padding(horizontal = 16.dp),
        backgroundColor = AppTheme.colorScheme.background1Neutral,
        topBar = {
            AppTopBar(
                title = "تغییر رمز عبور",
                onBack = {
                    navigationManager.navigateBack()
                })
        },
        footer = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AppButton(
                    modifier = Modifier.fillMaxWidth(),
                    enable = viewState.isAllPasswordOk,
                    title = "ادامه",
                    onClick = {
                        viewModel.handle(ChangePasswordViewActions.SubmitNewPassword)
                    })
            }
        }
    ) {

        AppPasswordTextField(
            value = viewState.oldPassword,
            label = "رمز عبور فعلی",
            onValueChange = {
                viewModel.handle(ChangePasswordViewActions.SetOldPassword(it))
            })

        Spacer(modifier = Modifier.height(24.dp))

        AppPasswordTextField(
            value = viewState.newPassword,
            label = "رمز عبور جدید",
            conditionMessage = true,
            onValidate = {
                viewModel.handle(ChangePasswordViewActions.SetNewPasswordValid(it.isValid))
            },
            onValueChange = {
                viewModel.handle(ChangePasswordViewActions.SetNewPassword(it))
            })

        Spacer(modifier = Modifier.height(24.dp))

        AppPasswordTextField(
            value = viewState.renewPassword,
            label = "تکرار رمز عبور جدید",
            onValueChange = {
                viewModel.handle(ChangePasswordViewActions.SetRenewPassword(it))
            })
    }
    if (viewState.loading) AppLoading()
    viewState.alertState?.let { AlertComponent(it) }
}

@AppPreview
@Composable
private fun ChangePasswordScreenPreview() {
    HamrahBankTheme {
        ChangePasswordScreen()
    }
}