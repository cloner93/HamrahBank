package com.pmb.profile.presentaion

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.pmb.navigation.moduleScreen.ProfileScreens
import com.pmb.profile.presentaion.profile.ProfileScreen


fun NavGraphBuilder.profileScreensHandle() {
    composable(route = ProfileScreens.Profile.route) {
        ProfileScreen(
//            viewModel = hiltViewModel<ProfileViewModel>()
        )
    }
}