package com.pmb.auth.presentation.register

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import androidx.navigation.navigation
import com.pmb.auth.presentation.register.account_opening.AccountOpeningScreen
import com.pmb.auth.presentation.register.account_opening.viewModel.OpeningAccountViewModel
import com.pmb.auth.presentation.register.authentication_information.AuthenticationInformationScreen
import com.pmb.auth.presentation.register.authentication_information.viewModel.AuthenticationInformationViewModel
import com.pmb.auth.presentation.register.authentication_select_services.AuthenticationSelectServicesScreen
import com.pmb.auth.presentation.register.authentication_select_services.viewModel.AuthenticationSelectServicesViewModel
import com.pmb.auth.presentation.register.check_postal_code.CheckPostalCodeScreen
import com.pmb.auth.presentation.register.check_postal_code.viewModel.CheckPostalCodeViewModel
import com.pmb.auth.presentation.register.choose_card.ChooseCardScreen
import com.pmb.auth.presentation.register.deposit_information.DepositInformationScreen
import com.pmb.auth.presentation.register.deposit_information.viewModel.DepositInformationViewModel
import com.pmb.auth.presentation.register.fee_details.FeeDetailsScreen
import com.pmb.auth.presentation.register.fee_details.viewModel.FeeDetailsViewModel
import com.pmb.auth.presentation.register.job_information.JobInformationScreen
import com.pmb.auth.presentation.register.job_information.viewModel.JobInformationViewModel
import com.pmb.auth.presentation.register.national_id.RegisterNationalIdScreen
import com.pmb.auth.presentation.register.national_id.viewModel.RegisterNationalIdViewModel
import com.pmb.auth.presentation.register.preparation.PreparationScreen
import com.pmb.auth.presentation.register.register_confirm.RegisterConfirmStepScreen
import com.pmb.auth.presentation.register.register_confirm.viewModel.RegisterConfirmStepViewModel
import com.pmb.auth.presentation.register.register_face_photo.RegisterFacePhotoCaptureScreen
import com.pmb.auth.presentation.register.register_face_photo.viewModel.RegisterFacePhotoCapturedViewModel
import com.pmb.auth.presentation.register.register_verify.RegisterConfirmScreen
import com.pmb.auth.presentation.register.register_verify.viewModel.RegisterConfirmViewModel
import com.pmb.auth.presentation.register.register_video.RegisterVideoScreen
import com.pmb.auth.presentation.register.register_video.viewModel.RegisterCapturingVideoViewModel
import com.pmb.auth.presentation.register.search_opening_branch.SearchOpeningBranchScreen
import com.pmb.auth.presentation.register.search_opening_branch.viewModel.SearchOpeningBranchViewModel
import com.pmb.auth.presentation.register.select_job_information.SelectJobInformationScreen
import com.pmb.auth.presentation.register.select_job_information.viewModel.SelectJobInformationViewModel
import com.pmb.auth.presentation.register.signature.SignatureScreen
import com.pmb.auth.presentation.register.signature.viewModel.SignatureViewModel
import com.pmb.navigation.manager.navigationManager
import com.pmb.navigation.moduleScreen.RegisterScreens

fun NavGraphBuilder.registerScreenHandler() {

    navigation(
        route = RegisterScreens.RegisterGraph.route,
        startDestination = RegisterScreens.Preparation.route
    ) {
        composable(route = RegisterScreens.Preparation.route) {

            PreparationScreen()
        }
        composable(route = RegisterScreens.Register.route) {
            val sharedViewModel =
                it.navigationManager.retrieveSharedViewModel<RegisterSharedViewModel>(
                    screen = RegisterScreens.RegisterGraph, navBackStackEntry = it
                )
            val sharedState = sharedViewModel.state.collectAsStateWithLifecycle()
            AccountOpeningScreen(
                viewModel = hiltViewModel<OpeningAccountViewModel>(),
                sharedState = sharedState
            ) { childState ->
                childState.phoneNumber?.let { it1 ->
                    sharedViewModel.updateState {
                        sharedState.value.copy(
                            phoneNumber = it1,
                        )
                    }
                }
                childState.nationalId?.let { it1 ->
                    sharedViewModel.updateState {
                        sharedState.value.copy(
                            nationalId = it1,
                        )
                    }
                }
            }
        }
        composable(route = RegisterScreens.RegisterNationalId.route) {
            val sharedViewModel =
                it.navigationManager.retrieveSharedViewModel<RegisterSharedViewModel>(
                    screen = RegisterScreens.RegisterGraph, navBackStackEntry = it
                )
            val sharedState = sharedViewModel.state.collectAsStateWithLifecycle()
            RegisterNationalIdScreen(
                viewModel = hiltViewModel<RegisterNationalIdViewModel>(),
            ) { childState ->
                childState.nationalSerialId?.let { serial ->
                    sharedViewModel.updateState {
                        sharedState.value.copy(
                            serialId = serial
                        )
                    }
                }
            }
        }
        composable(route = RegisterScreens.RegisterConfirm.route) {
            val sharedViewModel =
                it.navigationManager.retrieveSharedViewModel<RegisterSharedViewModel>(
                    screen = RegisterScreens.RegisterGraph, navBackStackEntry = it
                )
            val sharedState = sharedViewModel.state.collectAsStateWithLifecycle()
            RegisterConfirmScreen(
                sharedState = sharedState,
                viewModel = hiltViewModel<RegisterConfirmViewModel>(),
            ) { childState ->
                sharedViewModel.updateState {
                    sharedState.value.copy(
                        verifyCodeResponse = childState.verifyCodeResponse
                    )
                }
            }
        }
        composable(route = RegisterScreens.AuthenticationInformation.route) {
            val sharedViewModel =
                it.navigationManager.retrieveSharedViewModel<RegisterSharedViewModel>(
                    screen = RegisterScreens.RegisterGraph, navBackStackEntry = it
                )
            val sharedState = sharedViewModel.state.collectAsStateWithLifecycle()
            AuthenticationInformationScreen(
                viewModel = hiltViewModel<AuthenticationInformationViewModel>(),
                sharedState
            ) { childState ->
                sharedViewModel.updateState {
                    sharedState.value.copy(
                        birthDatePlace = childState.birthDatePlace,
                        issuePlace = childState.issuePlace,
                        tel = childState.tel,
                        education = childState.education,
                        issueCode = childState.issueCode,
                        issueDate = "${childState.issueDateYear}${childState.issueDateMonth}${childState.issueDateDay}".toInt()
                    )
                }
            }
        }
        composable(route = RegisterScreens.JobInformation.route) {
            val sharedViewModel =
                it.navigationManager.retrieveSharedViewModel<RegisterSharedViewModel>(
                    screen = RegisterScreens.RegisterGraph, navBackStackEntry = it
                )
            val sharedState = sharedViewModel.state.collectAsStateWithLifecycle()
            JobInformationScreen(
                viewModel = hiltViewModel<JobInformationViewModel>(),
                sharedState
            ) { childState ->
                childState.data?.let { it1 ->
                    sharedViewModel.updateState {
                        sharedState.value.copy(
                            annualIncomeType = it1
                        )
                    }
                }
            }
        }
        composable(route = RegisterScreens.SelectJobInformation.route) {
            SelectJobInformationScreen(
                viewModel = hiltViewModel<SelectJobInformationViewModel>()
            )
        }
        composable(route = RegisterScreens.CheckPostalCode.route) {
            CheckPostalCodeScreen(
                viewModel = hiltViewModel<CheckPostalCodeViewModel>()
            )
        }
        composable(route = RegisterScreens.DepositInformation.route) {
            val sharedViewModel =
                it.navigationManager.retrieveSharedViewModel<RegisterSharedViewModel>(
                    screen = RegisterScreens.RegisterGraph, navBackStackEntry = it
                )
            val sharedState = sharedViewModel.state.collectAsStateWithLifecycle()
            DepositInformationScreen(
                viewModel = hiltViewModel<DepositInformationViewModel>(),
                sharedState.value
            )
        }
        composable(
            route = RegisterScreens.SearchOpeningBranch.route,
            deepLinks = listOf(navDeepLink {
                uriPattern = "myapp://search_opening_branch/{provinceCode}/{provinceName}/{cityName}/{cityId}"
            }),
            arguments = listOf(
                navArgument("cityId") { type = NavType.IntType },
                navArgument("cityName") { type = NavType.StringType },
                navArgument("provinceCode") { type = NavType.IntType },
                navArgument("provinceName") { type = NavType.StringType },)
        ) {
            SearchOpeningBranchScreen(
                viewModel = hiltViewModel<SearchOpeningBranchViewModel>()
            )
        }
        composable(route = RegisterScreens.Signature.route) {
            SignatureScreen(
                viewModel = hiltViewModel<SignatureViewModel>()
            )
        }
        composable(route = RegisterScreens.AuthenticationSelectServices.route) {
            AuthenticationSelectServicesScreen(
                viewModel = hiltViewModel<AuthenticationSelectServicesViewModel>()
            )
        }
        composable(route = RegisterScreens.RegisterAuthentication.route) {
            RegisterAuthenticationScreen()
        }
        composable(route = RegisterScreens.RegisterFacePhotoCapture.route) {
            RegisterFacePhotoCaptureScreen(
                viewModel = hiltViewModel<RegisterFacePhotoCapturedViewModel>()
            )
        }
        composable(route = RegisterScreens.RegisterVideo.route) {
            RegisterVideoScreen(
                viewModel = hiltViewModel<RegisterCapturingVideoViewModel>()
            )
        }
        composable(route = RegisterScreens.RegisterConfirmStep.route) {
            RegisterConfirmStepScreen(
                viewModel = hiltViewModel<RegisterConfirmStepViewModel>(),
            )
        }
        composable(route = RegisterScreens.FeeDetails.route) {
            FeeDetailsScreen(
                viewModel = hiltViewModel<FeeDetailsViewModel>()
            )
        }
        composable(route = RegisterScreens.RegisterChooseCard.route) {
            ChooseCardScreen()
        }
    }
}