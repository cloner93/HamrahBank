package com.pmb.auth.presentaion.foget_password

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.pmb.auth.R
import com.pmb.auth.presentaion.component.ShowChangedNewPasswordBottomSheet
import com.pmb.auth.presentaion.foget_password.viewmodel.ForgetPasswordViewActions
import com.pmb.auth.presentaion.foget_password.viewmodel.ForgetPasswordViewEvents
import com.pmb.auth.presentaion.foget_password.viewmodel.ForgetPasswordViewModel
import com.pmb.ballon.component.AlertComponent
import com.pmb.ballon.component.base.AppButton
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppLoading
import com.pmb.ballon.component.base.AppMobileTextField
import com.pmb.ballon.component.base.AppNationalIdTextField
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.ballon.component.text_field.AppPasswordTextField
import com.pmb.core.presentation.NavigationManager

@Composable
fun ForgetPasswordScreen(navigationManager: NavigationManager, viewModel: ForgetPasswordViewModel) {
    var showBottomSheet by remember { mutableStateOf(false) }
    var mobile by remember { mutableStateOf("") }
    var nationalId by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var rePassword by remember { mutableStateOf("") }

    var isMobile by remember { mutableStateOf(false) }
    var isNationalId by remember { mutableStateOf(false) }
    var isPassword by remember { mutableStateOf(false) }
    var isRePassword by remember { mutableStateOf(false) }

    val viewState by viewModel.viewState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.viewEvent.collect { event ->
            when (event) {
                ForgetPasswordViewEvents.ResetPasswordSuccess -> showBottomSheet = true
            }
        }
    }

    AppContent(modifier = Modifier.padding(24.dp),
        topBar = {
            AppTopBar(title = stringResource(R.string.forget_password),
                onBack = { navigationManager.navigateBack() })
        },
        footer = {
            AppButton(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
                title = stringResource(R.string._continue),
                enable = !viewState.loading && isMobile && isNationalId && isPassword && isRePassword && password == rePassword || true,
                onClick = {
                    viewModel.handle(
                        ForgetPasswordViewActions.ResetPassword(
                            nationalId = nationalId, mobileNumber = mobile, password = password
                        )
                    )
                })
        },
        content = {

            AppMobileTextField(value = mobile,
                label = stringResource(R.string.phone_number),
                onValidate = { isMobile = it },
                onValueChange = { mobile = it })

            Spacer(modifier = Modifier.height(24.dp))

            AppNationalIdTextField(value = nationalId,
                label = stringResource(R.string.national_id),
                onValidate = { isNationalId = it },
                onValueChange = { nationalId = it })

            Spacer(modifier = Modifier.height(24.dp))

            AppPasswordTextField(value = password,
                label = stringResource(R.string.new_password),
                conditionMessage = true,
                onValidate = {
                    isPassword = it.isValid
                },
                onValueChange = { password = it })

            Spacer(modifier = Modifier.height(24.dp))

            AppPasswordTextField(value = rePassword,
                label = stringResource(R.string.re_new_password),
                conditionMessage = true,
                onValidate = {
                    isRePassword = it.isValid
                },
                onValueChange = { rePassword = it })

            Spacer(modifier = Modifier.height(24.dp))

        })

    if (showBottomSheet) ShowChangedNewPasswordBottomSheet(onDismiss = { showBottomSheet = false })
    if (viewState.loading) {
        AppLoading()
    }
    if (viewState.alert != null) {
        AlertComponent(viewState.alert!!)
    }
}