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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
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
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.core.utils.allowOnlyEnglishLettersDigitsAndSymbols
import com.pmb.core.utils.validatePhone
import com.pmb.navigation.manager.LocalNavigationManager
import com.pmb.navigation.manager.NavigationManager
import com.pmb.navigation.moduleScreen.ActivationScreens
import com.pmb.navigation.moduleScreen.AuthScreens

//
@Composable
fun FirstLoginScreen(
    viewModel: FirstLoginViewModel,
    updateShareState: (String, String, String) -> Unit
) {
    val navigationManager: NavigationManager = LocalNavigationManager.current
    val viewState by viewModel.viewState.collectAsState()
    val primaryStyle = SpanStyle(
        color = AppTheme.colorScheme.onBackgroundNeutralDefault,
        fontSize = 16.sp,
        fontWeight = FontWeight(416)
    )
    val secondaryStyle = SpanStyle(
        color = AppTheme.colorScheme.onBackgroundNeutralDefault,
        fontSize = 8.sp,
        fontWeight = FontWeight(416)
    )
    var isError by remember { mutableStateOf(false) }
    // Handle one-time events such as navigation or showing toasts
    LaunchedEffect(Unit) {
        viewModel.viewEvent.collect { event ->
            when (event) {
                FirsLoginViewEvents.FirstLoginStepSucceed -> {
                    updateShareState.invoke(
                        viewState.username,
                        viewState.phoneNumber,
                        viewState.password
                    )
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
                navigationManager.navigate(ActivationScreens.Activation)
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
            label = stringResource(com.pmb.auth.R.string.mobile_number),
            onFocused = { focused ->
                isError = if (!focused && !viewState.phoneNumber.isNullOrEmpty()) {
                    !viewState.phoneNumber.validatePhone()
                } else {
                    false
                }
            },
            isError = isError,
            errorText = stringResource(com.pmb.auth.R.string.validate_phone_number),
            onValueChange = { phone ->
                if (phone.isDigitsOnly() || phone.isEmpty())
                    viewModel.handle(FirstLoginViewActions.UpdatePhoneNumber(phone))
            }
        )

        Spacer(modifier = Modifier.size(24.dp))


        AppSingleTextField(
            modifier = Modifier.fillMaxWidth(),
            value = viewState.username,
            label = buildAnnotatedString {
                withStyle(style = primaryStyle) {
                    append("نام کاربری ")
                }
                withStyle(style = secondaryStyle) {
                    append("(شماره تلفن همراه برای اولین ورود)")
                }
            },
            onValueChange = {
                if (it.length <= 20 && it.allowOnlyEnglishLettersDigitsAndSymbols())
                    viewModel.handle(FirstLoginViewActions.UpdateUsername(it))
            },
        )

        Spacer(modifier = Modifier.size(24.dp))

        AppPasswordTextField(
            modifier = Modifier.fillMaxWidth(),
            value = viewState.password,
            label = stringResource(com.pmb.auth.R.string.login_password),
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
            title = stringResource(com.pmb.auth.R.string.forget_login_password),
            onClick = {
                navigationManager.navigate(AuthScreens.ForgetPassword)
            })
    }
    if (viewState.loading) AppLoading()
    viewState.alertModelState?.let { AlertComponent(it) }
}