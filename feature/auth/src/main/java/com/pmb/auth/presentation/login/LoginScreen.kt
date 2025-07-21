package com.pmb.auth.presentation.login

import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.pmb.auth.presentation.login.viewmodel.LoginViewActions
import com.pmb.auth.presentation.login.viewmodel.LoginViewEvents
import com.pmb.auth.presentation.login.viewmodel.LoginViewModel
import com.pmb.ballon.component.AlertComponent
import com.pmb.ballon.component.TextImage
import com.pmb.ballon.component.base.AppButtonWithIcon
import com.pmb.ballon.component.base.AppContent
import com.pmb.ballon.component.base.AppImage
import com.pmb.ballon.component.base.AppLoading
import com.pmb.ballon.component.base.AppTextButton
import com.pmb.ballon.component.base.BodySmallText
import com.pmb.ballon.component.base.IconType
import com.pmb.ballon.component.text_field.AppPasswordTextField
import com.pmb.ballon.models.ImageStyle
import com.pmb.ballon.models.Size
import com.pmb.ballon.models.TextStyle
import com.pmb.ballon.ui.theme.AppTheme
import com.pmb.navigation.manager.LocalNavigationManager
import com.pmb.navigation.manager.NavigationManager
import com.pmb.navigation.moduleScreen.AuthScreens
import com.pmb.navigation.moduleScreen.HomeScreens

@Composable
fun LoginScreen(viewModel: LoginViewModel) {
    val navigationManager: NavigationManager = LocalNavigationManager.current
    var password by remember { mutableStateOf("") }

    val viewState by viewModel.viewState.collectAsState()

    // Handle one-time events such as navigation or showing toasts
    LaunchedEffect(Unit) {
        viewModel.viewEvent.collect { event ->
            when (event) {
                LoginViewEvents.LoginSuccess -> {
                    navigationManager.navigate(HomeScreens.Home)
                }
            }
        }
    }
    AppContent(
        modifier = Modifier.padding(horizontal = 16.dp),
        topBar = {
            Spacer(modifier = Modifier.size(24.dp))
            Box(modifier = Modifier.fillMaxWidth()) {
                AppImage(
                    modifier = Modifier
                        .align(Alignment.Center),
                    image = painterResource(com.pmb.ballon.R.drawable.img_mellat_logo),
                    style = ImageStyle(size = Size.FIX(all = 56.dp))
                )
            }
        },
        footer = {

        },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.size(32.dp))
        TextImage(
            spacer = 0.dp,
            image = IconType.Painter(
                painterResource(com.pmb.ballon.R.drawable.profile_placeholder)
            ),
            text = viewState.userData?.fullName ?: "",
            imageStyle = ImageStyle(size = Size.FIX(all = 56.dp)),
            textStyle = TextStyle(
                color = AppTheme.colorScheme.onBackgroundNeutralDefault,
                typography = AppTheme.typography.headline4,
                textAlign = TextAlign.Center
            )
        )
        Spacer(modifier = Modifier.size(8.dp))
        BodySmallText(
            text = "${stringResource(com.pmb.auth.R.string.username)}: ${viewState.userData?.username}",
            color = AppTheme.colorScheme.onBackgroundPrimarySubdued
        )
        Spacer(modifier = Modifier.size(32.dp))
        AppPasswordTextField(
            value = password,
            label = stringResource(com.pmb.auth.R.string.password),
            conditionMessage = false,
            onValidate = {
//                isPassword = it.isValid
            },
            onValueChange = { password = it })
        Spacer(modifier = Modifier.size(24.dp))
        AppButtonWithIcon(
            modifier = Modifier
                .fillMaxWidth(),
            title = if (password.isEmpty()) stringResource(com.pmb.auth.R.string.enter_face_detection) else stringResource(
                com.pmb.auth.R.string.login
            ),
            icon = if (password.isEmpty()) com.pmb.ballon.R.drawable.ic_face_id else null,
            enable = true
        ) {
            if (password.isEmpty())
                navigationManager.navigate(AuthScreens.ReentryFaceDetection)
            else viewModel.handle(
                LoginViewActions.Login(
                    viewState.userData?.customerId ?: "",
                    viewState.userData?.username ?: "",
                    password
                )
            )
        }

        Spacer(modifier = Modifier.size(8.dp))

        AppTextButton(
            modifier = Modifier.fillMaxWidth(),
            title = stringResource(com.pmb.auth.R.string.forget_password),
            onClick = {
                navigationManager.navigate(AuthScreens.ForgetPassword)
            })
    }
    if (viewState.isLoading) {
        AppLoading()
    }
    if (viewState.alert != null) {
        AlertComponent(viewState.alert!!)
    }
}