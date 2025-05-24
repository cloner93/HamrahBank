package com.pmb.profile.presentaion

import android.util.Log
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.pmb.navigation.manager.navigationManager
import com.pmb.navigation.moduleScreen.ProfileScreens
import com.pmb.profile.presentaion.personal_infos.PersonalInfoSharedViewModel
import com.pmb.profile.presentaion.personal_infos.change_address.ChangeAddressScreen
import com.pmb.profile.presentaion.personal_infos.change_address.viewmodel.ChangeAddressViewModel
import com.pmb.profile.presentaion.personal_infos.change_education.ChangeEducationScreen
import com.pmb.profile.presentaion.personal_infos.change_education.viewmodel.ChangeEducationViewModel
import com.pmb.profile.presentaion.personal_infos.change_job.ChangeJobScreen
import com.pmb.profile.presentaion.personal_infos.change_job.viewmodel.ChangeJobViewModel
import com.pmb.profile.presentaion.personal_infos.change_phone_number.ChangePhoneNumberScreen
import com.pmb.profile.presentaion.personal_infos.change_phone_number.viewmodel.ChangePhoneNumberViewModel
import com.pmb.profile.presentaion.personal_infos.change_phone_number_otp.ChangePhoneNumberOtpScreen
import com.pmb.profile.presentaion.personal_infos.change_phone_number_otp.viewmodel.ChangePhoneNumberOtpViewModel
import com.pmb.profile.presentaion.personal_infos.change_username.ChangeUsernameScreen
import com.pmb.profile.presentaion.personal_infos.change_username.viewmodel.ChangeUsernameViewModel
import com.pmb.profile.presentaion.personal_infos.personal_info.PersonalInfoScreen
import com.pmb.profile.presentaion.personal_infos.personal_info.viewmodel.PersonalInfoViewModel
import com.pmb.profile.presentaion.personal_infos.select_job.SelectJobScreen
import com.pmb.profile.presentaion.personal_infos.select_job.viewmodel.SelectJobViewModel
import com.pmb.profile.presentaion.profile.ProfileScreen
import com.pmb.profile.presentaion.profile.viewModel.ProfileViewModel
import com.pmb.profile.presentaion.themeScreen.ThemeScreen
import com.pmb.profile.presentaion.themeScreen.viewmodel.ThemeScreenViewModel
import com.pmb.profile.presentaion.update.detail.viewmodel.DetailVersionViewModel
import com.pmb.profile.presentaion.update.latest.LatestVersionsScreen
import com.pmb.profile.presentaion.update.latest.viewmodel.LatestVersionsViewModel
import com.pmb.profile.presentaion.update.status.UpdateStatusScreen
import com.pmb.profile.presentaion.update.status.viewmodel.UpdateStatusViewModel
import com.pmb.profile.presentaion.update.viewmodel.DetailVersionScreen


fun NavGraphBuilder.profileScreensHandle() {
    composable(route = ProfileScreens.Profile.route) {
        ProfileScreen(viewModel = hiltViewModel<ProfileViewModel>())
    }

    composable(route = ProfileScreens.ThemeScreen.route) {
        ThemeScreen(
            viewModel = hiltViewModel<ThemeScreenViewModel>()
        )
    }

    navigation(
        route = ProfileScreens.PersonalInfo.Graph.route,
        startDestination = ProfileScreens.PersonalInfo.Info.route
    ) {

        composable(route = ProfileScreens.PersonalInfo.Info.route) {
            val sharedViewModel =
                it.navigationManager.retrieveSharedViewModel<PersonalInfoSharedViewModel>(
                    screen = ProfileScreens.PersonalInfo.Graph, navBackStackEntry = it
                )
            val sharedState = sharedViewModel.state.collectAsStateWithLifecycle()
            PersonalInfoScreen(
                viewModel = hiltViewModel<PersonalInfoViewModel>(), sharedState = sharedState.value
            ) {
                sharedViewModel.updateState {
                    copy(
                        username = it.username,
                        phoneNumber = it.phoneNumber,
                        addressEntity = it.addressEntity,
                        jobEntity = it.jobEntity,
                        educationEntity = it.educationEntity,
                        queueJob = null
                    )
                }
            }
        }
        composable(route = ProfileScreens.PersonalInfo.ChangeUsername.route) {
            val sharedViewModel =
                it.navigationManager.retrieveSharedViewModel<PersonalInfoSharedViewModel>(
                    screen = ProfileScreens.PersonalInfo.Graph, navBackStackEntry = it
                )
            val sharedState = sharedViewModel.state.collectAsStateWithLifecycle()
            ChangeUsernameScreen(
                viewModel = hiltViewModel<ChangeUsernameViewModel>(),
                sharedState = sharedState.value
            ) { personalEntity ->
                Log.d("sharedState", "RESULT in $route")
                sharedViewModel.updateState { copy(username = personalEntity.username) }
            }
        }
        composable(route = ProfileScreens.PersonalInfo.ChangePhoneNumber.route) {
            val sharedViewModel =
                it.navigationManager.retrieveSharedViewModel<PersonalInfoSharedViewModel>(
                    screen = ProfileScreens.PersonalInfo.Graph, navBackStackEntry = it
                )
            val sharedState = sharedViewModel.state.collectAsStateWithLifecycle()
            ChangePhoneNumberScreen(
                viewModel = hiltViewModel<ChangePhoneNumberViewModel>(),
                sharedState = sharedState.value
            ) { otpEntity ->
                sharedViewModel.updateState { copy(otpEntity = otpEntity) }
            }
        }

        composable(route = ProfileScreens.PersonalInfo.VerifyPhoneNumberOtp.route) {
            val sharedViewModel =
                it.navigationManager.retrieveSharedViewModel<PersonalInfoSharedViewModel>(
                    screen = ProfileScreens.PersonalInfo.Graph, navBackStackEntry = it
                )
            val sharedState = sharedViewModel.state.collectAsStateWithLifecycle()
            ChangePhoneNumberOtpScreen(
                viewModel = hiltViewModel<ChangePhoneNumberOtpViewModel>(),
                shareState = sharedState.value,
            ) {
                sharedViewModel.updateState {
                    copy(
                        phoneNumber = it.phoneNumber,
                        otpEntity = null
                    )
                }
            }
        }

        composable(route = ProfileScreens.PersonalInfo.ChangeAddress.route) {
            val sharedViewModel =
                it.navigationManager.retrieveSharedViewModel<PersonalInfoSharedViewModel>(
                    screen = ProfileScreens.PersonalInfo.Graph, navBackStackEntry = it
                )
            val sharedState = sharedViewModel.state.collectAsStateWithLifecycle()
            ChangeAddressScreen(
                viewModel = hiltViewModel<ChangeAddressViewModel>(),
                shareState = sharedState.value,
                result = {
                    sharedViewModel.updateState { copy(addressEntity = it) }
                }
            )
        }
        composable(route = ProfileScreens.PersonalInfo.ChangeJob.route) {
            val sharedViewModel =
                it.navigationManager.retrieveSharedViewModel<PersonalInfoSharedViewModel>(
                    screen = ProfileScreens.PersonalInfo.Graph, navBackStackEntry = it
                )
            val sharedState = sharedViewModel.state.collectAsStateWithLifecycle()
            ChangeJobScreen(
                viewModel = hiltViewModel<ChangeJobViewModel>(),
                sharedState = sharedState.value,
                result = {
                    sharedViewModel.setState(it)
                }
            )
        }
        composable(route = ProfileScreens.PersonalInfo.SelectJob.route) {
            val sharedViewModel =
                it.navigationManager.retrieveSharedViewModel<PersonalInfoSharedViewModel>(
                    screen = ProfileScreens.PersonalInfo.Graph, navBackStackEntry = it
                )
            val sharedState = sharedViewModel.state.collectAsStateWithLifecycle()
            SelectJobScreen(
                viewModel = hiltViewModel<SelectJobViewModel>(),
                sharedState = sharedState.value,
                result = {
                    sharedViewModel.updateState { copy(queueJob = it) }
                }
            )

        }
        composable(route = ProfileScreens.PersonalInfo.ChangeEducation.route) {
            val sharedViewModel =
                it.navigationManager.retrieveSharedViewModel<PersonalInfoSharedViewModel>(
                    screen = ProfileScreens.PersonalInfo.Graph, navBackStackEntry = it
                )
            val sharedState = sharedViewModel.state.collectAsStateWithLifecycle()
            ChangeEducationScreen(
                viewModel = hiltViewModel<ChangeEducationViewModel>(),
                sharedState = sharedState.value,
                result = {
                    sharedViewModel.updateState { copy(educationEntity = it) }
                }
            )
        }
    }

    navigation(
        route = ProfileScreens.Update.Graph.route,
        startDestination = ProfileScreens.Update.Status.route
    ) {
        composable(route = ProfileScreens.Update.Status.route) {
            val sharedViewModel =
                it.navigationManager.retrieveSharedViewModel<DetailVersionViewModel>(
                    screen = ProfileScreens.Update.Graph, navBackStackEntry = it
                )
            UpdateStatusScreen(
                viewModel = hiltViewModel<UpdateStatusViewModel>(),
                result = {
                    sharedViewModel.setVersion(it)
                }
            )
        }
        composable(route = ProfileScreens.Update.Latest.route) {
            val sharedViewModel =
                it.navigationManager.retrieveSharedViewModel<DetailVersionViewModel>(
                    screen = ProfileScreens.Update.Graph, navBackStackEntry = it
                )
            LatestVersionsScreen(
                viewModel = hiltViewModel<LatestVersionsViewModel>(),
                result = {
                    sharedViewModel.setVersion(it)
                }
            )
        }

        composable(route = ProfileScreens.Update.Detail.route) {
            val sharedViewModel =
                it.navigationManager.retrieveSharedViewModel<DetailVersionViewModel>(
                    screen = ProfileScreens.Update.Graph, navBackStackEntry = it
                )
            DetailVersionScreen(viewModel = sharedViewModel)
        }
    }
}