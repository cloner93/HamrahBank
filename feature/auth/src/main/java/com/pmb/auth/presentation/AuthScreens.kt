package com.pmb.auth.presentation

import android.util.Log
import androidx.compose.runtime.mutableStateOf
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
import com.pmb.auth.presentation.preparation.PreparationScreen
import com.pmb.auth.presentation.reentry.reentry_face_detection.ReentryFaceDetectionScreen
import com.pmb.auth.presentation.reentry.reentry_face_detection.viewModel.ReentryFaceDetectionViewModel
import com.pmb.auth.presentation.reentry.reentry_password.ReentryPasswordScreen
import com.pmb.auth.presentation.reentry.reentry_password.viewModel.ReentryPasswordViewModel
import com.pmb.auth.presentation.register.account_opening.AccountOpeningScreen
import com.pmb.auth.presentation.register.authentication_information.AuthenticationInformationScreen
import com.pmb.auth.presentation.register.authentication_information.viewModel.AuthenticationInformationViewModel
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
import com.pmb.navigation.manager.LocalNavigationManager
import com.pmb.navigation.moduleScreen.AuthScreens


fun NavGraphBuilder.authScreensHandle(
) {
    var comingType = mutableStateOf<ComingType?>(null)
    composable(route = AuthScreens.Auth.route) {
        IntroScreen()
    }
    composable(route = AuthScreens.FirstLogin.route) {
        FirstLoginScreen(
            viewModel = hiltViewModel<FirstLoginViewModel>()
        )
    }
    composable(
        route = AuthScreens.FirstLoginConfirm.route,
        deepLinks = listOf(navDeepLink {
            uriPattern = "myapp://first_login_confirm/{mobileNumber}/{username}/{password}"
        }),
        arguments = listOf(
            navArgument("mobileNumber") { type = NavType.StringType },
            navArgument("username") { type = NavType.StringType },
            navArgument("password") { type = NavType.StringType })
    ) {
        FirstLoginConfirmScreen(
            viewModel = hiltViewModel<FirstLoginConfirmViewModel>(),
            comingType = comingType.value ?: ComingType.COMING_LOGIN
        )
    }
    composable(route = AuthScreens.Login.route) {
        LoginScreen(
            viewModel = hiltViewModel<LoginViewModel>()
        )
    }
    composable(route = AuthScreens.Register.route) {
        val navigationManager = LocalNavigationManager.current
        AccountOpeningScreen {
            comingType = mutableStateOf(it)
            navigationManager.navigate(AuthScreens.RegisterNationalId)
        }
    }
    composable(route = AuthScreens.ForgetPassword.route) {
        val navigationManager = LocalNavigationManager.current
        ForgetPasswordScreen(
            viewModel = hiltViewModel<ForgetPasswordViewModel>()
        ) {
            comingType = mutableStateOf(it)
            navigationManager.navigate(AuthScreens.ChooseAuthenticationType)
        }
    }
    composable(route = AuthScreens.ForgetPasswordAuth.route) {
        ForgetPasswordAuthScreen()
    }
    composable(route = AuthScreens.RegisterNationalId.route) {
        RegisterNationalIdScreen(
            viewModel = hiltViewModel<RegisterNationalIdViewModel>(),
            comingType = comingType.value ?: ComingType.COMING_REGISTER
        )
    }
    composable(route = AuthScreens.Signature.route) {
        SignatureScreen(
            viewModel = hiltViewModel<SignatureViewModel>()
        )
    }
    composable(route = AuthScreens.Authentication.route) {
        AuthenticationScreen()
    }
    composable(route = AuthScreens.FacePhotoCapture.route) {
        FacePhotoCaptureScreen(
            viewModel = hiltViewModel<FacePhotoCapturedViewModel>()
        )
    }
    composable(route = AuthScreens.AuthenticationVideo.route) {
        val navigationManager = LocalNavigationManager.current
        AuthenticationVideoScreen(
            viewModel = hiltViewModel<AuthenticationCapturingVideoViewModel>()
        ) {
            navigationManager.navigateAndClearStack(AuthScreens.AuthenticationConfirmStep)
            navigationManager.setCurrentScreenData<ComingType>(
                "authentication",
                comingType.value ?: ComingType.COMING_REGISTER
            )
            Log.d("comingType", comingType.value.toString())
        }
    }
    composable(route = AuthScreens.AuthenticationConfirmStep.route) {
        AuthenticationConfirmScreen(
            viewModel = hiltViewModel<AuthenticationConfirmStepViewModel>(),
        )
    }
    composable(route = AuthScreens.AuthenticationSelectServices.route) {
        AuthenticationSelectServicesScreen(
            viewModel = hiltViewModel<AuthenticationSelectServicesViewModel>()
        )
    }
    composable(route = AuthScreens.FeeDetails.route) {
        FeeDetailsScreen(
            viewModel = hiltViewModel<FeeDetailsViewModel>()
        )
    }
    composable(route = AuthScreens.OpenAccount.route) {
        OpenAccountScreen(
            viewModel = hiltViewModel<OpenAccountViewModel>()
        )
    }
    composable(route = AuthScreens.CheckPostalCode.route) {
        CheckPostalCodeScreen(
            viewModel = hiltViewModel<CheckPostalCodeViewModel>()
        )
    }
    composable(route = AuthScreens.DepositInformation.route) {
        DepositInformationScreen(
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
            viewModel = hiltViewModel<SearchOpeningBranchViewModel>()
        )
    }
    composable(route = AuthScreens.SelectJobInformation.route) {
        SelectJobInformationScreen(
            viewModel = hiltViewModel<SelectJobInformationViewModel>()
        )
    }
    composable(route = AuthScreens.ReentryPassword.route) {
        ReentryPasswordScreen(
            viewModel = hiltViewModel<ReentryPasswordViewModel>()
        )
    }
    composable(route = AuthScreens.ReentryFaceDetection.route) {
        ReentryFaceDetectionScreen(
            viewModel = hiltViewModel<ReentryFaceDetectionViewModel>()
        )
    }
    composable(route = AuthScreens.ChooseAuthenticationType.route) {
        ChooseAuthenticationTypeScreen(
            comingType = comingType.value ?: ComingType.COMING_ACTIVATION
        )
    }
    composable(route = AuthScreens.Activation.route) {
        ActivationScreen(viewModel = hiltViewModel<ActivationViewModel>())
    }
    composable(route = AuthScreens.ActivationTaxDetailsScreen.route) {
        val navigationManager = LocalNavigationManager.current
        ActivationTaxDetailsScreen(
            viewModel = hiltViewModel<ActivationTaxDetailsViewModel>()
        ) {

            comingType = mutableStateOf(it)
            navigationManager.navigate(AuthScreens.ChooseAuthenticationType)
        }
    }
    composable(
        route = AuthScreens.CardInformation.route
    ) {
        CardInfoScreen(
            viewModel = hiltViewModel<CardInfoViewModel>(),
            comingType = comingType.value ?: ComingType.COMING_ACTIVATION
        )
    }
    composable(route = AuthScreens.ScanCard.route) {
        ScanCardScreen(
            viewModel = hiltViewModel<ScanCardViewModel>()
        )
    }
    composable(route = AuthScreens.CardInformationConfirmation.route) {
        CardInformationConfirmScreen(
            viewModel = hiltViewModel<CardInformationConfirmViewModel>()
        )
    }
    composable(route = AuthScreens.JobInformation.route) {
        JobInformationScreen(
            viewModel = hiltViewModel<JobInformationViewModel>()
        )
    }
    composable(route = AuthScreens.AuthenticationInformation.route) {
        AuthenticationInformationScreen(
            viewModel = hiltViewModel<AuthenticationInformationViewModel>()
        )
    }
    composable(route = AuthScreens.Preparation.route) {
        PreparationScreen()
    }
}
