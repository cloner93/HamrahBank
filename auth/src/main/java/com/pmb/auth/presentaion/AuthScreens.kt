package com.pmb.auth.presentaion

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.pmb.auth.presentaion.first_login.FirstLoginScreen
import com.pmb.auth.presentaion.first_login.viewModel.FirstLoginViewModel
import com.pmb.auth.presentaion.first_login_confirm.FirstLoginConfirmScreen
import com.pmb.auth.presentaion.first_login_confirm.viewModel.FirstLoginConfirmViewModel
import com.pmb.auth.presentaion.foget_password.ForgetPasswordAuthScreen
import com.pmb.auth.presentaion.foget_password.ForgetPasswordScreen
import com.pmb.auth.presentaion.foget_password.viewmodel.ForgetPasswordViewModel
import com.pmb.auth.presentaion.intro.IntroScreen
import com.pmb.auth.presentaion.login.LoginScreen
import com.pmb.auth.presentaion.login.viewmodel.LoginViewModel
import com.pmb.core.presentation.NavigationManager
import com.pmb.core.presentation.Screen


sealed class AuthScreens(route: String, arguments: Map<String, String> = emptyMap()) :
    Screen(route = route, arguments = arguments) {
    data object Auth : AuthScreens(route = "auth")
    data object FirstLogin : AuthScreens(route = "first_login")
    data object FirstLoginConfirm : AuthScreens(route = "first_login_confirm")
    data object Login : AuthScreens(route = "login")
    data object Register : AuthScreens(route = "register")
    data object ForgetPassword : AuthScreens(route = "forget_password")
    data object ForgetPasswordAuth : AuthScreens(route = "forget_password_auth")
}


fun NavGraphBuilder.authScreensHandle(
    navigationManager: NavigationManager,
) {
    composable(route = AuthScreens.Auth.route) {
        IntroScreen(navigationManager = navigationManager)
    }
    composable(route = AuthScreens.FirstLogin.route) {
        FirstLoginScreen(navigationManager = navigationManager, viewModel = hiltViewModel<FirstLoginViewModel>())
    }
    composable(route = AuthScreens.FirstLoginConfirm.route) {
        FirstLoginConfirmScreen(navigationManager = navigationManager,viewModel = hiltViewModel<FirstLoginConfirmViewModel>())
    }
    composable(route = AuthScreens.Login.route) {
        LoginScreen(
            navigationManager = navigationManager,
            viewModel = hiltViewModel<LoginViewModel>()
        )
    }
    composable(route = AuthScreens.Register.route) {

    }
    composable(route = AuthScreens.ForgetPassword.route) {
        ForgetPasswordScreen(
            navigationManager = navigationManager,
            viewModel = hiltViewModel<ForgetPasswordViewModel>()
        )
    }
    composable(route = AuthScreens.ForgetPasswordAuth.route) {
        ForgetPasswordAuthScreen(navigationManager = navigationManager)
    }
}