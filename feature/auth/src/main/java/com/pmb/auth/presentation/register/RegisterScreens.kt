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
import com.pmb.auth.presentation.register.authentication_information.BirthDatePlaceScreen
import com.pmb.auth.presentation.register.authentication_information.IssueCityPlace
import com.pmb.auth.presentation.register.authentication_information.viewModel.AuthenticationInformationViewModel
import com.pmb.auth.presentation.register.authentication_select_services.AuthenticationSelectServicesScreen
import com.pmb.auth.presentation.register.authentication_select_services.viewModel.AuthenticationSelectServicesViewModel
import com.pmb.auth.presentation.register.check_postal_code.CheckPostalCodeScreen
import com.pmb.auth.presentation.register.check_postal_code.viewModel.CheckPostalCodeViewModel
import com.pmb.auth.presentation.register.choose_card.ChooseCardScreen
import com.pmb.auth.presentation.register.choose_card.viewModel.ChooseCardViewModel
import com.pmb.auth.presentation.register.deposit_information.CitySearchScreen
import com.pmb.auth.presentation.register.deposit_information.DepositInformationScreen
import com.pmb.auth.presentation.register.deposit_information.ProvinceSearchScreen
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
                sharedViewModel.updateState {
                    sharedState.value.copy(
                        mobileNo = childState.phoneNumber,
                        nationalCode = childState.nationalId,
                        birthDate = "${childState.birthDateYear}${childState.birthDateMonth}${childState.birthDateDay}",
                    )
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
                sharedState.value
            ) { childState ->
                childState.nationalSerialId?.let { serial ->
                    sharedViewModel.updateState {
                        sharedState.value.copy(
                            seriMeli = serial.substring(0, 2),
                            serialMeli = serial.substring(2, 10)
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
                        birthCityCode = childState.birthDatePlace?.cityCode,
                        tel = childState.tel,
                        education = childState.education?.id,
                        issueCityCode = childState.issuePlace?.cityCode,
                        issueRgnCode = childState.issueCode,
                        issueDate = "${childState.issueDateYear}${childState.issueDateMonth}${childState.issueDateDay}"
                    )
                }
            }
        }
        composable(route = RegisterScreens.SelectBirthDatePlace.route) {
            val sharedViewModel =
                it.navigationManager.retrieveSharedViewModel<RegisterSharedViewModel>(
                    screen = RegisterScreens.RegisterGraph, navBackStackEntry = it
                )
            val sharedState = sharedViewModel.state.collectAsStateWithLifecycle()
            BirthDatePlaceScreen(sharedState.value) {
                sharedViewModel.updateState {
                    sharedState.value.copy(
                        birthDatePlaceCity = it
                    )
                }
            }
        }
        composable(route = RegisterScreens.SelectIssuePlace.route) {
            val sharedViewModel =
                it.navigationManager.retrieveSharedViewModel<RegisterSharedViewModel>(
                    screen = RegisterScreens.RegisterGraph, navBackStackEntry = it
                )
            val sharedState = sharedViewModel.state.collectAsStateWithLifecycle()
            IssueCityPlace(sharedState.value) {
                sharedViewModel.updateState {
                    sharedState.value.copy(
                        issuePlaceCity = it
                    )
                }
            }
        }
        composable(route = RegisterScreens.SelectCityPlace.route) {
            val sharedViewModel =
                it.navigationManager.retrieveSharedViewModel<RegisterSharedViewModel>(
                    screen = RegisterScreens.RegisterGraph, navBackStackEntry = it
                )
            val sharedState = sharedViewModel.state.collectAsStateWithLifecycle()
            CitySearchScreen(sharedState.value) {
                sharedViewModel.updateState {
                    sharedState.value.copy(
                        cityOfDeposit = it
                    )
                }
            }
        }
        composable(route = RegisterScreens.SelectProvincePlace.route) {
            val sharedViewModel =
                it.navigationManager.retrieveSharedViewModel<RegisterSharedViewModel>(
                    screen = RegisterScreens.RegisterGraph, navBackStackEntry = it
                )
            val sharedState = sharedViewModel.state.collectAsStateWithLifecycle()
            ProvinceSearchScreen(sharedState.value) {
                sharedViewModel.updateState {
                    sharedState.value.copy(
                        provinceOfDeposit = it
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
                            jobCode = childState.jobInformation?.jobCode,
                            cinCpId = it1.incomePredictId
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
            val sharedViewModel =
                it.navigationManager.retrieveSharedViewModel<RegisterSharedViewModel>(
                    screen = RegisterScreens.RegisterGraph, navBackStackEntry = it
                )
            val sharedState = sharedViewModel.state.collectAsStateWithLifecycle()
            CheckPostalCodeScreen(
                viewModel = hiltViewModel<CheckPostalCodeViewModel>(),
                sharedState.value
            ) { post, address ->
                sharedViewModel.updateState {
                    sharedState.value.copy(
                        postcode = post.toLong(),
                        address = address
                    )
                }
            }
        }
        composable(route = RegisterScreens.DepositInformation.route) {
            val sharedViewModel =
                it.navigationManager.retrieveSharedViewModel<RegisterSharedViewModel>(
                    screen = RegisterScreens.RegisterGraph, navBackStackEntry = it
                )
            val sharedState = sharedViewModel.state.collectAsStateWithLifecycle()
            DepositInformationScreen(
                viewModel = hiltViewModel<DepositInformationViewModel>(),
                sharedState.value,
                setProvince = {
                    sharedViewModel.updateState {
                        sharedState.value.copy(
                            provinceList = it,
                            cityOfDeposit = null
                        )
                    }
                },
                setCity = {
                    sharedViewModel.updateState {
                        sharedState.value.copy(
                            cityList = it
                        )
                    }
                }
            ) { childState ->
                sharedViewModel.updateState {
                    sharedState.value.copy(
                        accType = childState.accType?.accountType,
                        branch = branch
                    )
                }
            }
        }
        composable(
            route = RegisterScreens.SearchOpeningBranch.route, deepLinks = listOf(navDeepLink {
                uriPattern =
                    "myapp://search_opening_branch/{provinceCode}/{provinceName}/{cityName}/{cityId}"
            }), arguments = listOf(
                navArgument("provinceCode") { type = NavType.IntType },
                navArgument("provinceName") { type = NavType.StringType },
                navArgument("cityName") { type = NavType.StringType },
                navArgument("cityId") { type = NavType.IntType },
            )
        ) {
            SearchOpeningBranchScreen(
                viewModel = hiltViewModel<SearchOpeningBranchViewModel>()
            )
        }
        composable(route = RegisterScreens.Signature.route) {
            val sharedViewModel =
                it.navigationManager.retrieveSharedViewModel<RegisterSharedViewModel>(
                    screen = RegisterScreens.RegisterGraph, navBackStackEntry = it
                )
            val sharedState = sharedViewModel.state.collectAsStateWithLifecycle()
            SignatureScreen(
                viewModel = hiltViewModel<SignatureViewModel>()
            ) { childState ->
                sharedViewModel.updateState {
                    sharedState.value.copy(
                        signData = childState
                    )
                }
            }
        }
        composable(route = RegisterScreens.AuthenticationSelectServices.route) {
            val sharedViewModel =
                it.navigationManager.retrieveSharedViewModel<RegisterSharedViewModel>(
                    screen = RegisterScreens.RegisterGraph, navBackStackEntry = it
                )
            val sharedState = sharedViewModel.state.collectAsStateWithLifecycle()
            AuthenticationSelectServicesScreen(
                viewModel = hiltViewModel<AuthenticationSelectServicesViewModel>()
            ) { childState ->
                sharedViewModel.updateState {
                    sharedState.value.copy(
                        cardReq = if (childState.localData?.selectServicesList?.findLast { id == 1 }?.isChecked?.value == true) 1 else 0,
                        intBankReq = if (childState.localData?.selectServicesList?.findLast { id == 0 }?.isChecked?.value == true) 1 else 0
                    )
                }
            }
        }
        composable(route = RegisterScreens.RegisterAuthentication.route) {
            RegisterAuthenticationScreen()
        }
        composable(route = RegisterScreens.RegisterFacePhotoCapture.route) {
            val sharedViewModel =
                it.navigationManager.retrieveSharedViewModel<RegisterSharedViewModel>(
                    screen = RegisterScreens.RegisterGraph, navBackStackEntry = it
                )
            val sharedState = sharedViewModel.state.collectAsStateWithLifecycle()
            RegisterFacePhotoCaptureScreen(
                viewModel = hiltViewModel<RegisterFacePhotoCapturedViewModel>()
            ) { childState ->
                sharedViewModel.updateState {
                    sharedState.value.copy(
                        authImage = childState
                    )
                }
            }
        }
        composable(route = RegisterScreens.RegisterVideo.route) {
            val sharedViewModel =
                it.navigationManager.retrieveSharedViewModel<RegisterSharedViewModel>(
                    screen = RegisterScreens.RegisterGraph, navBackStackEntry = it
                )
            val sharedState = sharedViewModel.state.collectAsStateWithLifecycle()
            RegisterVideoScreen(
                viewModel = hiltViewModel<RegisterCapturingVideoViewModel>(),
                sharedState.value
            ) { childState ->
                sharedViewModel.updateState {
                    sharedState.value.copy(
                        refId = childState
                    )
                }
            }
        }
        composable(route = RegisterScreens.RegisterConfirmStep.route) {
            val sharedViewModel =
                it.navigationManager.retrieveSharedViewModel<RegisterSharedViewModel>(
                    screen = RegisterScreens.RegisterGraph, navBackStackEntry = it
                )
            val sharedState = sharedViewModel.state.collectAsStateWithLifecycle()
            RegisterConfirmStepScreen(
                viewModel = hiltViewModel<RegisterConfirmStepViewModel>(),
                sharedState.value
            )
        }
        composable(route = RegisterScreens.FeeDetails.route) {
            FeeDetailsScreen(
                viewModel = hiltViewModel<FeeDetailsViewModel>()
            )
        }
        composable(route = RegisterScreens.RegisterChooseCard.route) {
            val sharedViewModel =
                it.navigationManager.retrieveSharedViewModel<RegisterSharedViewModel>(
                    screen = RegisterScreens.RegisterGraph, navBackStackEntry = it
                )
            val sharedState = sharedViewModel.state.collectAsStateWithLifecycle()
            ChooseCardScreen(
                viewModel = hiltViewModel<ChooseCardViewModel>()
            ) { childState ->
                sharedViewModel.updateState {
                    sharedState.value.copy(
                        cardFormatId = childState.selectedCard?.formatId
                    )
                }
            }
        }
    }
}