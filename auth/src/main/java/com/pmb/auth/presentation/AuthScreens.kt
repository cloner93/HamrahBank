package com.pmb.auth.presentation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.pmb.auth.presentation.ekyc.authentication.AuthenticationScreen
import com.pmb.auth.presentation.ekyc.authentication_confirm.AuthenticationConfirmScreen
import com.pmb.auth.presentation.ekyc.authentication_confirm.viewModel.AuthenticationConfirmStepViewModel
import com.pmb.auth.presentation.ekyc.authentication_select_services.AuthenticationSelectServicesScreen
import com.pmb.auth.presentation.ekyc.authentication_select_services.viewModel.AuthenticationSelectServicesViewModel
import com.pmb.auth.presentation.ekyc.authentication_video.AuthenticationVideoScreen
import com.pmb.auth.presentation.ekyc.authentication_video.viewModel.AuthenticationCapturingVideoViewModel
import com.pmb.auth.presentation.ekyc.face_photo.FacePhotoCaptureScreen
import com.pmb.auth.presentation.ekyc.face_photo.viewModel.FacePhotoCapturedViewModel
import com.pmb.auth.presentation.ekyc.fee_details.FeeDetailsScreen
import com.pmb.auth.presentation.ekyc.fee_details.viewModel.FeeDetailsViewModel
import com.pmb.auth.presentation.ekyc.open_account.OpenAccountScreen
import com.pmb.auth.presentation.ekyc.open_account.viewModel.OpenAccountViewModel
import com.pmb.auth.presentation.ekyc.signature.SignatureScreen
import com.pmb.auth.presentation.ekyc.signature.viewModel.SignatureViewModel
import com.pmb.auth.presentation.first_login.FirstLoginScreen
import com.pmb.auth.presentation.first_login.viewModel.FirstLoginViewModel
import com.pmb.auth.presentation.first_login_confirm.FirstLoginConfirmScreen
import com.pmb.auth.presentation.first_login_confirm.viewModel.FirstLoginConfirmViewModel
import com.pmb.auth.presentation.foget_password.ForgetPasswordAuthScreen
import com.pmb.auth.presentation.foget_password.ForgetPasswordScreen
import com.pmb.auth.presentation.foget_password.viewmodel.ForgetPasswordViewModel
import com.pmb.auth.presentation.intro.IntroScreen
import com.pmb.auth.presentation.login.LoginScreen
import com.pmb.auth.presentation.login.viewmodel.LoginViewModel
import com.pmb.auth.presentation.register.account_opening.AccountOpeningScreen
import com.pmb.auth.presentation.register.national_id.RegisterNationalIdScreen
import com.pmb.auth.presentation.register.national_id.viewModel.RegisterNationalIdViewModel
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
    data object RegisterNationalId : AuthScreens(route = "register_national_id")
    data object Signature : AuthScreens(route = "signature")
    data object Authentication : AuthScreens(route = "authentication")
    data object FacePhotoCapture : AuthScreens(route = "face_photo_capture")
    data object AuthenticationVideo : AuthScreens(route = "authentication_video")
    data object AuthenticationConfirmStep : AuthScreens(route = "authentication_confirm_step")
    data object AuthenticationSelectServices : AuthScreens(route = "authentication_select_services")
    data object FeeDetails : AuthScreens(route = "fee_details")
    data object OpenAccount : AuthScreens(route = "open_account")
}


fun NavGraphBuilder.authScreensHandle(
    navigationManager: NavigationManager,
) {
    composable(route = AuthScreens.Auth.route) {
        IntroScreen(navigationManager = navigationManager)
    }
    composable(route = AuthScreens.FirstLogin.route) {
        FirstLoginScreen(
            navigationManager = navigationManager,
            viewModel = hiltViewModel<FirstLoginViewModel>()
        )
    }
    composable(route = AuthScreens.FirstLoginConfirm.route) {
        FirstLoginConfirmScreen(
            navigationManager = navigationManager,
            viewModel = hiltViewModel<FirstLoginConfirmViewModel>()
        )
    }
    composable(route = AuthScreens.Login.route) {
        LoginScreen(
            navigationManager = navigationManager,
            viewModel = hiltViewModel<LoginViewModel>()
        )
    }
    composable(route = AuthScreens.Register.route) {
        AccountOpeningScreen(navigationManager = navigationManager)
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
    composable(route = AuthScreens.RegisterNationalId.route) {
        RegisterNationalIdScreen(
            navigationManager = navigationManager,
            viewModel = hiltViewModel<RegisterNationalIdViewModel>()
        )
    }
    composable(route = AuthScreens.Signature.route) {
        SignatureScreen(
            navigationManager = navigationManager,
            viewModel = hiltViewModel<SignatureViewModel>()
        )
    }
    composable(route = AuthScreens.Authentication.route) {
        AuthenticationScreen(navigationManager = navigationManager)
    }
    composable(route = AuthScreens.FacePhotoCapture.route) {
        FacePhotoCaptureScreen(
            navigationManager = navigationManager,
            viewModel = hiltViewModel<FacePhotoCapturedViewModel>()
        )
    }
    composable(route = AuthScreens.AuthenticationVideo.route) {
        AuthenticationVideoScreen(
            navigationManager = navigationManager,
            viewModel = hiltViewModel<AuthenticationCapturingVideoViewModel>()
        )
    }
    composable(route = AuthScreens.AuthenticationConfirmStep.route) {
        AuthenticationConfirmScreen(
            navigationManager = navigationManager,
            viewModel = hiltViewModel<AuthenticationConfirmStepViewModel>()
        )
    }
    composable(route = AuthScreens.AuthenticationSelectServices.route) {
        AuthenticationSelectServicesScreen(
            navigationManager = navigationManager,
            viewModel = hiltViewModel<AuthenticationSelectServicesViewModel>()
        )
    }
    composable(route = AuthScreens.FeeDetails.route) {
        FeeDetailsScreen(
            navigationManager = navigationManager,
            viewModel = hiltViewModel<FeeDetailsViewModel>()
        )
    }
    composable(route = AuthScreens.OpenAccount.route) {
        OpenAccountScreen(
            navigationManager = navigationManager,
            viewModel = hiltViewModel<OpenAccountViewModel>()
        )
    }
}