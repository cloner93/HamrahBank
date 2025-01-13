package com.pmb.auth.presentaion.activate

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.pmb.auth.R
import com.pmb.auth.presentaion.activate.viewModel.ActivateViewActions
import com.pmb.auth.presentaion.activate.viewModel.ActivateViewEvents
import com.pmb.auth.presentaion.activate.viewModel.ActivateViewModel
import com.pmb.auth.presentaion.first_login_confirm.viewModel.FirstLoginConfirmViewEvents
import com.pmb.ballon.component.AlertComponent
import com.pmb.ballon.component.base.AppButton
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppLoading
import com.pmb.ballon.component.base.AppMobileTextField
import com.pmb.ballon.component.base.AppNationalIdTextField
import com.pmb.ballon.component.base.AppSingleTextField
import com.pmb.ballon.component.text_field.AppPasswordTextField
import com.pmb.home.presentation.HomeScreens

@Composable
fun ActivateScreen(navController: NavController, viewModel: ActivateViewModel) {
    var mobile by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var nationalId by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val viewState by viewModel.viewState.collectAsState()
    var isMobile by remember { mutableStateOf(false) }
    var isNationalId by remember { mutableStateOf(false) }
    var isUsername by remember { mutableStateOf(false) }
    var isPassword by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        viewModel.viewEvent.collect { event ->
            when (event) {
                ActivateViewEvents.ActiveUserSucceed -> {
                }
            }
        }
    }
    AppContent(
        modifier = Modifier.padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        footer = {
            AppButton(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
                title = stringResource(R.string._continue),
                enable = !viewState.loading && isMobile && isNationalId && isUsername && isPassword,
                onClick = {
                    viewModel.handle(
                        ActivateViewActions.ActiveUser(
                            mobileNumber = mobile,
                            password = password,
                            nationalId = nationalId,
                            userName = username
                        )
                    )
                })

        },
        content = {
            Image(
                modifier = Modifier.size(56.dp),
                painter = painterResource(com.pmb.ballon.R.drawable.img_mellat_logo),
                contentDescription = "mellat logo"
            )

            Spacer(modifier = Modifier.size(32.dp))

            AppMobileTextField(value = mobile,
                label = stringResource(R.string.phone_number),
                onValidate = { isMobile = it },
                onValueChange = { mobile = it })

            Spacer(modifier = Modifier.size(24.dp))

            AppNationalIdTextField(value = nationalId,
                label = stringResource(R.string.national_id),
                onValidate = { isNationalId = it },
                onValueChange = { nationalId = it })

            Spacer(modifier = Modifier.height(24.dp))

            AppSingleTextField(
                modifier = Modifier.fillMaxWidth(),
                value = username,
                label = stringResource(com.pmb.auth.R.string.username),
                onValueChange = {
                    username = it
                    isUsername = it.length >= 6
                },
            )

            Spacer(modifier = Modifier.size(24.dp))

            AppPasswordTextField(modifier = Modifier.fillMaxWidth(),
                value = password,
                label = stringResource(com.pmb.auth.R.string.password),
                onValidate = { isPassword = it.isValid },
                onValueChange = { password = it })
        })
    if (viewState.loading) {
        AppLoading()
    }
    if (viewState.alertModelState != null) {
        AlertComponent(viewState.alertModelState!!)
    }
}


