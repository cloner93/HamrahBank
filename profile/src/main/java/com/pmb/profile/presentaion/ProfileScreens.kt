package com.pmb.profile.presentaion

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.pmb.navigation.moduleScreen.ProfileScreens
import com.pmb.profile.presentaion.profile.ProfileScreen
import com.pmb.profile.presentaion.themeScreen.ThemeScreen

fun NavGraphBuilder.profileScreensHandle() {
    composable(route = ProfileScreens.Profile.route) {
        ProfileScreen(
//            viewModel = hiltViewModel<ProfileViewModel>()
        )
    }

    composable(route = ProfileScreens.ThemeScreen.route) {
        ThemeScreen(
//            viewModel = hiltViewModel<ProfileViewModel>()
        )
    }
}