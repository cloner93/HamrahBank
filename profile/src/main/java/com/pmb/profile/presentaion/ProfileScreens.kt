package com.pmb.profile.presentaion

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.pmb.navigation.moduleScreen.ProfileScreens
import com.pmb.profile.presentaion.personal_info.PersonalInfoScreen
import com.pmb.profile.presentaion.personal_info.viewmodel.PersonalInfoViewModel
import com.pmb.profile.presentaion.profile.ProfileScreen
import com.pmb.profile.presentaion.profile.viewModel.ProfileViewModel


fun NavGraphBuilder.profileScreensHandle() {
    composable(route = ProfileScreens.Profile.route) {
        ProfileScreen(
            viewModel = hiltViewModel<ProfileViewModel>()
        )
    }

    composable(route = ProfileScreens.PersonalInfo.route) {
        PersonalInfoScreen(
            viewModel = hiltViewModel<PersonalInfoViewModel>()
        )
    }
    composable(route = ProfileScreens.ChangeUsername.route) {

    }
    composable(route = ProfileScreens.ChangePhoneNumber.route) {

    }
    composable(route = ProfileScreens.ChangeAddress.route) {

    }
    composable(route = ProfileScreens.ChangeJob.route) {

    }
    composable(route = ProfileScreens.ChangeEducation.route) {

    }

}