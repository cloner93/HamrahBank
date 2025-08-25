package com.pmb.auth.presentation.new_password

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
import androidx.navigation.NavController
import com.pmb.auth.R
import com.pmb.auth.presentation.component.ShowChangedNewPasswordBottomSheet
import com.pmb.auth.presentation.new_password.viewModel.NewPassWordViewEvents
import com.pmb.auth.presentation.new_password.viewModel.NewPassWordViewModel
import com.pmb.auth.presentation.new_password.viewModel.NewPasswordViewActions
import com.pmb.ballon.component.AlertComponent
import com.pmb.ballon.component.base.AppButton
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppLoading
import com.pmb.ballon.component.base.AppTopBar
import com.pmb.ballon.component.text_field.AppPasswordTextField

@Composable
fun NewPasswordScreen(navController: NavController, viewModel: NewPassWordViewModel) {
    var password by remember { mutableStateOf("") }
    var rePassword by remember { mutableStateOf("") }
    var isPassword by remember { mutableStateOf(false) }
    var isRePassword by remember { mutableStateOf(false) }
    val viewState by viewModel.viewState.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.viewEvent.collect { event ->
            when (event) {
                NewPassWordViewEvents.ChangePassWordSucceed -> {
                    //TODO - change when we want to do something and we call it in AuthScreens.kt
                }
            }
        }
    }
    AppContent(
        modifier = Modifier.padding(24.dp),
        topBar = {
            AppTopBar(
                title = stringResource(R.string.select_new_password),
                onBack = { navController.navigateUp() })
        },
        footer = {
            AppButton(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
                title = stringResource(R.string._continue),
                enable = !viewState.loading && isPassword && isRePassword && password == rePassword,
                onClick = {
                    viewModel.handle(
                        NewPasswordViewActions.ChangePassWord(
                            passWord = password,
                            mobileNumber = viewModel.getAccountSampleModel().mobileNumber,
                            userName = viewModel.getAccountSampleModel().userName
                        )
                    )
                })
        },
        content = {
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
        })

    if (viewState.isShowBottomSheet)
        ShowChangedNewPasswordBottomSheet(onDismiss = {
            viewModel.handle(
                NewPasswordViewActions.ClearBottomSheet
            )
        })
    if (viewState.loading) {
        AppLoading()
    }
    if (viewState.alertModelState != null) {
        AlertComponent(viewState.alertModelState!!)
    }
}

