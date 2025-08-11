package com.pmb.auth.presentation.foget_password

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import com.pmb.auth.AuthActionType
import com.pmb.auth.AuthSharedViewState
import com.pmb.auth.R
import com.pmb.auth.presentation.component.ShowChangedNewPasswordBottomSheet
import com.pmb.auth.presentation.foget_password.viewmodel.ForgetPasswordViewActions
import com.pmb.auth.presentation.foget_password.viewmodel.ForgetPasswordViewEvents
import com.pmb.auth.presentation.foget_password.viewmodel.ForgetPasswordViewModel
import com.pmb.auth.presentation.foget_password.viewmodel.ForgetPasswordViewState
import com.pmb.ballon.component.AlertComponent
import com.pmb.ballon.component.base.AppButton
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppLoading
import com.pmb.ballon.component.base.AppMobileTextField
import com.pmb.ballon.component.base.AppNationalIdTextField
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.ballon.component.text_field.AppPasswordTextField
import com.pmb.core.utils.allowOnlyEnglishLettersAndDigits
import com.pmb.core.utils.validatePhone
import com.pmb.navigation.manager.LocalNavigationManager
import com.pmb.navigation.manager.NavigationManager
import com.pmb.navigation.moduleScreen.AuthScreens

@Composable
fun ForgetPasswordScreen(
    viewModel: ForgetPasswordViewModel,
    sharedState: AuthSharedViewState,
    updatePasswordChangedState: (Boolean) -> Unit,
    updateSharedState: (AuthActionType,ForgetPasswordViewState) -> Unit
) {
    val navigationManager: NavigationManager = LocalNavigationManager.current
    var showBottomSheet by remember { mutableStateOf(false) }

    var isPassword by remember { mutableStateOf(false) }
    var isRePassword by remember { mutableStateOf(false) }
    var isError by remember { mutableStateOf(false) }
    val viewState by viewModel.viewState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.viewEvent.collect { event ->
            when (event) {
                ForgetPasswordViewEvents.ResetPasswordSuccess -> {
                    updateSharedState.invoke(
                        AuthActionType.FORGET_PASSWORD,viewState
                    )
                    navigationManager.navigate(AuthScreens.ChooseAuthenticationType)
                }
            }
        }
    }
    LaunchedEffect(Unit) {
        if (sharedState.isPasswordChanged) {
            showBottomSheet = true
        }
    }

    AppContent(
        modifier = Modifier.padding(24.dp),
        topBar = {
            AppTopBar(
                title = stringResource(R.string.forget_login_password),
                onBack = { navigationManager.navigateBack() })
        },
        footer = {
            AppButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                title = stringResource(R.string._continue),
                enable = !viewState.loading && viewState.password == viewState.rePassword && isRePassword && isPassword,
                onClick = {
                    viewModel.handle(
                        ForgetPasswordViewActions.ResetPassword
                    )
                })
        },
        content = {

            AppMobileTextField(
                value = viewState.mobile ?: "",
                label = stringResource(R.string.mobile_number),
                onFocused = { focused ->
                    isError = if (!focused && !viewState.mobile.isNullOrEmpty()) {
                        !viewState.mobile.validatePhone()
                    } else {
                        false
                    }
                },
                isError = isError,
                errorText = stringResource(R.string.validate_phone_number),
                onValueChange = { phone ->
                    if (phone.isDigitsOnly() || phone.isEmpty())
                        viewModel.handle(ForgetPasswordViewActions.SetMobile(phone))
                }
            )

            Spacer(modifier = Modifier.height(24.dp))

            AppNationalIdTextField(
                value = viewState.nationalId ?: "",
                label = stringResource(R.string.national_id),
                onValidate = { },
                onValueChange = {
                    if (it.isDigitsOnly() || it.isEmpty())
                        viewModel.handle(ForgetPasswordViewActions.SetNationalId(it))
                }
            )

            Spacer(modifier = Modifier.height(24.dp))

            AppPasswordTextField(
                value = viewState.password ?: "",
                label = stringResource(R.string.new_password),
                conditionMessage = true,
                onValidate = {

                },
                onValueChange = {
                    if (it.allowOnlyEnglishLettersAndDigits() || it.isEmpty()) {
                        viewModel.handle(ForgetPasswordViewActions.SetNewPassword(it))
                        isPassword =
                            it.length >= 10
                    }
                })

            Spacer(modifier = Modifier.height(24.dp))

            AppPasswordTextField(
                value = viewState.rePassword ?: "",
                label = stringResource(R.string.re_new_password),
                conditionMessage = true,
                onValidate = {

                },
                onValueChange = {
                    if (it.allowOnlyEnglishLettersAndDigits() || it.isEmpty()) {
                        viewModel.handle(ForgetPasswordViewActions.SetReNewPassword(it))
                        isRePassword =
                             (it.length) >= 10
                    }
                })

            Spacer(modifier = Modifier.height(24.dp))

        })

    if (showBottomSheet) ShowChangedNewPasswordBottomSheet(onDismiss = {
        updatePasswordChangedState(false)
        navigationManager.navigate(AuthScreens.Auth)
    })
    if (viewState.loading) {
        AppLoading()
    }
    if (viewState.alertModelState != null) {
        AlertComponent(viewState.alertModelState!!)
    }
}