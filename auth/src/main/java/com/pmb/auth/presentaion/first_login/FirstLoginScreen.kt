package com.pmb.auth.presentaion.first_login

import androidx.compose.foundation.Image
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
import androidx.compose.ui.unit.dp
import com.pmb.auth.presentaion.AuthScreens
import com.pmb.auth.presentaion.first_login.viewModel.FirsLoginViewEvents
import com.pmb.auth.presentaion.first_login.viewModel.FirstLoginViewActions
import com.pmb.auth.presentaion.first_login.viewModel.FirstLoginViewModel
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
import com.pmb.core.presentation.NavigationManager

@Composable
fun FirstLoginScreen(navigationManager: NavigationManager, viewModel: FirstLoginViewModel) {
    var phonenumber by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isMobile by remember { mutableStateOf(false) }
    var isUsername by remember { mutableStateOf(false) }
    var isPassword by remember { mutableStateOf(false) }

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

    AppContent(
        modifier = Modifier.padding(horizontal = 24.dp),
        topBar = {
            AppTopBar(
                title = stringResource(com.pmb.auth.R.string.login_to_hamrah_bank),
                onBack = { navigationManager.navigateBack() })
        },
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
        footer = {
            AppOutlineButton(modifier = Modifier
                .padding(horizontal = 24.dp)
                .fillMaxWidth(),
                title = stringResource(com.pmb.auth.R.string.hamrah_bank_activate),
                onClick = {

                })
        }
    ) {
//        Column(
//            modifier = Modifier
//                .padding(24.dp),
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
        Spacer(modifier = Modifier.size(24.dp))

        Image(
            modifier = Modifier.size(56.dp),
            painter = painterResource(R.drawable.img_mellat_logo),
            contentDescription = "mellat logo"
        )

        Spacer(modifier = Modifier.size(32.dp))

        AppMobileTextField(value = phonenumber,
            label = stringResource(com.pmb.auth.R.string.phone_number),
            onValidate = { isMobile = it },
            onValueChange = { phonenumber = it })

        Spacer(modifier = Modifier.size(32.dp))


        AppSingleTextField(
            modifier = Modifier.fillMaxWidth(),
            value = username,
            label = stringResource(com.pmb.auth.R.string.username),
            onValueChange = {
                username = it
                isUsername = it.length >= 6
            },
        )

        Spacer(modifier = Modifier.size(32.dp))

        AppPasswordTextField(modifier = Modifier.fillMaxWidth(),
            value = password,
            label = stringResource(com.pmb.auth.R.string.password),
            onValueChange = {
                password = it
                isPassword = it.length >= 6
            })

        Spacer(modifier = Modifier.size(32.dp))

        AppButton(modifier = Modifier.fillMaxWidth(),
            title = stringResource(com.pmb.auth.R.string._continue),
            enable = isMobile && isPassword && isUsername,
            onClick = {
                viewModel.handle(
                    FirstLoginViewActions.FirstLoginStepConfirm(
                        userName = username, mobileNumber = phonenumber, password = password
                    )
                )
            })

        Spacer(modifier = Modifier.size(8.dp))

        AppTextButton(modifier = Modifier.fillMaxWidth(),
            title = stringResource(com.pmb.auth.R.string.forget_password),
            onClick = {
                navigationManager.navigate(AuthScreens.ForgetPassword)
            })

//            Spacer(modifier = Modifier.weight(1f))

//        }
    }
    if (viewState.loading) {
        AppLoading()
    }
    if (viewState.alertModelState != null) {
        AlertComponent(viewState.alertModelState!!)
    }
}