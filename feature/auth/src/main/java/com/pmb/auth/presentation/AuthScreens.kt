package com.pmb.auth.presentation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.pmb.auth.AuthSharedViewModel
import com.pmb.auth.presentation.activation.activationScreenHandler
import com.pmb.auth.presentation.activation.choose_authentication_type.ActivationAuthenticationTypeScreen
import com.pmb.auth.presentation.ekyc.authentication_confirm.AuthenticationConfirmScreen
import com.pmb.auth.presentation.ekyc.authentication_confirm.viewModel.AuthenticationConfirmStepViewModel
import com.pmb.auth.presentation.ekyc.ekycScreenHandler
import com.pmb.auth.presentation.ekyc.open_account.OpenAccountScreen
import com.pmb.auth.presentation.ekyc.open_account.viewModel.OpenAccountViewModel
import com.pmb.auth.presentation.fingerprint.EnableFingerprintScreen
import com.pmb.auth.presentation.fingerprint.viewmodel.EnableFingerprintViewModel
import com.pmb.auth.presentation.first_login.FirstLoginScreen
import com.pmb.auth.presentation.first_login.viewModel.FirstLoginViewModel
import com.pmb.auth.presentation.first_login_confirm.FirstLoginConfirmScreen
import com.pmb.auth.presentation.first_login_confirm.viewModel.FirstLoginConfirmViewModel
import com.pmb.auth.presentation.foget_password.ForgetPasswordAuthScreen
import com.pmb.auth.presentation.foget_password.ForgetPasswordScreen
import com.pmb.auth.presentation.foget_password.viewmodel.ForgetPasswordViewModel
import com.pmb.auth.presentation.intro.IntroScreen
import com.pmb.auth.presentation.intro.viewModel.IntroViewModel
import com.pmb.auth.presentation.login.LoginScreen
import com.pmb.auth.presentation.login.viewmodel.LoginViewModel
import com.pmb.auth.presentation.reentry.reentry_face_detection.ReentryFaceDetectionScreen
import com.pmb.auth.presentation.reentry.reentry_face_detection.viewModel.ReentryFaceDetectionViewModel
import com.pmb.auth.presentation.reentry.reentry_password.ReentryPasswordScreen
import com.pmb.auth.presentation.reentry.reentry_password.viewModel.ReentryPasswordViewModel
import com.pmb.auth.presentation.register.registerScreenHandler
import com.pmb.auth.presentation.scan_card_info.cardScreenHandler
import com.pmb.navigation.manager.navigationManager
import com.pmb.navigation.moduleScreen.AuthScreens
import com.pmb.navigation.moduleScreen.SharedAuthAndActivationScopeGraph


fun NavGraphBuilder.authScreensHandle(
) {
    navigation(
        route = AuthScreens.AuthGraph.route,
        startDestination = AuthScreens.Auth.route
    ) {
        composable(route = AuthScreens.Auth.route) {
            IntroScreen(viewModel = hiltViewModel<IntroViewModel>())
        }
        composable(route = AuthScreens.FirstLogin.route) {
            val sharedViewModel =
                it.navigationManager.retrieveSharedViewModel<AuthSharedViewModel>(
                    screen = AuthScreens.AuthGraph, navBackStackEntry = it
                )
            val sharedState = sharedViewModel.state.collectAsStateWithLifecycle()
            FirstLoginScreen(
                viewModel = hiltViewModel<FirstLoginViewModel>()
            ) { userName, phoneNumber, password ->
                sharedViewModel.updateState {
                    sharedState.value.copy(
                        phoneNumber = phoneNumber,
                        userName = userName,
                        password = password
                    )
                }
            }
        }
        composable(
            route = AuthScreens.FirstLoginConfirm.route,
//            deepLinks = listOf(navDeepLink {
//                uriPattern = "myapp://first_login_confirm/{mobileNumber}/{username}/{password}"
//            }),
//            arguments = listOf(
//                navArgument("mobileNumber") { type = NavType.StringType },
//                navArgument("username") { type = NavType.StringType },
//                navArgument("password") { type = NavType.StringType })
        ) {
            val sharedViewModel =
                it.navigationManager.retrieveSharedViewModel<AuthSharedViewModel>(
                    screen = AuthScreens.AuthGraph, navBackStackEntry = it
                )
            val sharedState = sharedViewModel.state.collectAsStateWithLifecycle()
            FirstLoginConfirmScreen(
                viewModel = hiltViewModel<FirstLoginConfirmViewModel>(),
                sharedState = sharedState
            )
//            FirstLoginConfirmScreen(
//                viewModel = hiltViewModel<FirstLoginConfirmViewModel>(),
//                comingType = comingType.value ?: ComingType.COMING_LOGIN
//            )
        }
        composable(route = AuthScreens.Login.route) {
            LoginScreen(
                viewModel = hiltViewModel<LoginViewModel>()
            )
        }
        composable(route = AuthScreens.EnableFingerprint.route) {
            EnableFingerprintScreen(
                viewModel = hiltViewModel<EnableFingerprintViewModel>()
            )
        }

        composable(route = AuthScreens.ForgetPassword.route) {
            val sharedViewModel =
                it.navigationManager.retrieveSharedViewModel<AuthSharedViewModel>(
                    screen = SharedAuthAndActivationScopeGraph, navBackStackEntry = it
                )
            val sharedState = sharedViewModel.state.collectAsStateWithLifecycle()
            ForgetPasswordScreen(
                viewModel = hiltViewModel<ForgetPasswordViewModel>(),
                sharedState = sharedState,
                updatePasswordChangedState = {
                    sharedViewModel.updateState {
                        sharedState.value.copy(
                            isPasswordChanged = it
                        )
                    }
                }
            ) {
                sharedViewModel.updateState {
                    sharedState.value.copy(
                        actionType = it
                    )
                }
            }
        }
        composable(route = AuthScreens.ForgetPasswordAuth.route) {
            ForgetPasswordAuthScreen()
        }
        composable(route = AuthScreens.AuthenticationConfirmStep.route) {
            AuthenticationConfirmScreen(
                viewModel = hiltViewModel<AuthenticationConfirmStepViewModel>(),
            )
        }


        composable(route = AuthScreens.OpenAccount.route) {
            OpenAccountScreen(
                viewModel = hiltViewModel<OpenAccountViewModel>()
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
            val sharedViewModel =
                it.navigationManager.retrieveSharedViewModel<AuthSharedViewModel>(
                    screen = SharedAuthAndActivationScopeGraph, navBackStackEntry = it
                )
            val sharedState = sharedViewModel.state.collectAsStateWithLifecycle()
            ActivationAuthenticationTypeScreen(
                sharedState
            )
        }
    }
    cardScreenHandler()
    registerScreenHandler()
    ekycScreenHandler()
    activationScreenHandler()
}
