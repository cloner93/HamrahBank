package com.pmb.auth.presentation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.pmb.auth.presentation.activation.activate.ActivationScreen
import com.pmb.auth.presentation.activation.activate.viewModel.ActivationViewModel
import com.pmb.auth.presentation.activation.activation_tax_details.ActivationTaxDetailsScreen
import com.pmb.auth.presentation.activation.activation_tax_details.viewModel.ActivationTaxDetailsViewModel
import com.pmb.auth.presentation.activation.choose_authentication_type.ChooseAuthenticationTypeScreen
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
import com.pmb.auth.presentation.reentry.reentry_face_detection.ReentryFaceDetectionScreen
import com.pmb.auth.presentation.reentry.reentry_face_detection.viewModel.ReentryFaceDetectionViewModel
import com.pmb.auth.presentation.reentry.reentry_password.ReentryPasswordScreen
import com.pmb.auth.presentation.reentry.reentry_password.viewModel.ReentryPasswordViewModel
import com.pmb.auth.presentation.register.account_opening.AccountOpeningScreen
import com.pmb.auth.presentation.register.check_postal_code.CheckPostalCodeScreen
import com.pmb.auth.presentation.register.check_postal_code.viewModel.CheckPostalCodeViewModel
import com.pmb.auth.presentation.register.deposit_information.DepositInformationScreen
import com.pmb.auth.presentation.register.deposit_information.viewModel.DepositInformationViewModel
import com.pmb.auth.presentation.register.job_information.JobInformationScreen
import com.pmb.auth.presentation.register.job_information.viewModel.JobInformationViewModel
import com.pmb.auth.presentation.register.national_id.RegisterNationalIdScreen
import com.pmb.auth.presentation.register.national_id.viewModel.RegisterNationalIdViewModel
import com.pmb.auth.presentation.register.search_opening_branch.SearchOpeningBranchScreen
import com.pmb.auth.presentation.register.search_opening_branch.viewModel.SearchOpeningBranchViewModel
import com.pmb.auth.presentation.register.select_job_information.SelectJobInformationScreen
import com.pmb.auth.presentation.register.select_job_information.viewModel.SelectJobInformationViewModel
import com.pmb.auth.presentation.scan_card_info.card_confirm.CardInformationConfirmScreen
import com.pmb.auth.presentation.scan_card_info.card_confirm.viewModel.CardInformationConfirmViewModel
import com.pmb.auth.presentation.scan_card_info.card_info.CardInfoScreen
import com.pmb.auth.presentation.scan_card_info.card_info.viewModel.CardInfoViewModel
import com.pmb.auth.presentation.scan_card_info.scan_card.ScanCardScreen
import com.pmb.auth.presentation.scan_card_info.scan_card.viewModel.ScanCardViewModel
import com.pmb.auth.utils.ComingType
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
    data object SelectJobInformation :
        AuthScreens(route = "select_job_information")

    data object JobInformation : AuthScreens(route = "job_information")
    data object CheckPostalCode : AuthScreens(route = "check_postal_code")
    data object DepositInformation : AuthScreens(route = "deposit_information")
    data object SearchOpeningBranch :
        AuthScreens(route = "search_opening_branch/{provinceName}/{cityName}/{cityId}") {
        fun createRoute(cityId: Int, cityName: String, provinceName: String) =
            "search_opening_branch/$provinceName/$cityName/$cityId"
    }

    data object Signature : AuthScreens(route = "signature")
    data object Authentication : AuthScreens(route = "authentication")
    data object FacePhotoCapture : AuthScreens(route = "face_photo_capture")
    data object AuthenticationVideo : AuthScreens(route = "authentication_video")
    data object AuthenticationConfirmStep : AuthScreens(route = "authentication_confirm_step")
    data object AuthenticationSelectServices : AuthScreens(route = "authentication_select_services")
    data object FeeDetails : AuthScreens(route = "fee_details")
    data object OpenAccount : AuthScreens(route = "open_account")
    data object ReentryPassword : AuthScreens(route = "reentry_password")
    data object ReentryFaceDetection : AuthScreens(route = "reentry_face_detection")
    data object ChooseAuthenticationType : AuthScreens(route = "choose_authentication_type")
    data object Activation : AuthScreens(route = "activation")
    data object ActivationTaxDetailsScreen : AuthScreens(route = "activation_tax_details")
    data object CardInformation : AuthScreens(route = "card_information")
    data object ScanCard : AuthScreens(route = "scan_card")
    data object CardInformationConfirmation : AuthScreens("card_information_confirmation")
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
    composable(route = AuthScreens.CheckPostalCode.route) {
        CheckPostalCodeScreen(
            navigationManager = navigationManager,
            viewModel = hiltViewModel<CheckPostalCodeViewModel>()
        )
    }
    composable(route = AuthScreens.DepositInformation.route) {
        DepositInformationScreen(
            navigationManager = navigationManager,
            viewModel = hiltViewModel<DepositInformationViewModel>()
        )
    }

    composable(
        route = AuthScreens.SearchOpeningBranch.route,
        deepLinks = listOf(navDeepLink {
            uriPattern = "myapp://search_opening_branch/{provinceName}/{cityName}/{cityId}"
        }),
        arguments = listOf(
            navArgument("cityId") { type = NavType.IntType },
            navArgument("cityName") { type = NavType.StringType },
            navArgument("provinceName") { type = NavType.StringType })
    ) {
        SearchOpeningBranchScreen(
            navigationManager,
            viewModel = hiltViewModel<SearchOpeningBranchViewModel>()
        )
    }
    composable(route = AuthScreens.SelectJobInformation.route) {
        SelectJobInformationScreen(
            navigationManager,
            viewModel = hiltViewModel<SelectJobInformationViewModel>()
        )
    }
    composable(route = AuthScreens.ReentryPassword.route) {
        ReentryPasswordScreen(
            navigationManager = navigationManager,
            viewModel = hiltViewModel<ReentryPasswordViewModel>()
        )
    }
    composable(route = AuthScreens.ReentryFaceDetection.route) {
        ReentryFaceDetectionScreen(
            navigationManager = navigationManager,
            viewModel = hiltViewModel<ReentryFaceDetectionViewModel>()
        )
    }
    composable(route = AuthScreens.ChooseAuthenticationType.route) {
        ChooseAuthenticationTypeScreen(navigationManager, comingType = ComingType.COMING_PASSWORD)
    }
    composable(route = AuthScreens.Activation.route) {
        ActivationScreen(navigationManager, viewModel = hiltViewModel<ActivationViewModel>())
    }
    composable(route = AuthScreens.ActivationTaxDetailsScreen.route) {
        ActivationTaxDetailsScreen(
            navigationManager,
            viewModel = hiltViewModel<ActivationTaxDetailsViewModel>()
        )
    }
    composable(
        route = AuthScreens.CardInformation.route
    ) {
        CardInfoScreen(
            navigationManager,
            viewModel = hiltViewModel<CardInfoViewModel>()
        )
    }
    composable(route = AuthScreens.ScanCard.route) {
        ScanCardScreen(
            navigationManager,
            viewModel = hiltViewModel<ScanCardViewModel>()
        )
    }
    composable(route = AuthScreens.CardInformationConfirmation.route) {
        CardInformationConfirmScreen(
            navigationManager,
            viewModel = hiltViewModel<CardInformationConfirmViewModel>()
        )
    }
    composable(route = AuthScreens.JobInformation.route) {
        JobInformationScreen(
            navigationManager,
            viewModel = hiltViewModel<JobInformationViewModel>()
        )
    }
}