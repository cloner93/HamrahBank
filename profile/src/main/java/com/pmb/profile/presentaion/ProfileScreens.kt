package com.pmb.profile.presentaion

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.pmb.core.presentation.NavigationManager
import com.pmb.core.presentation.Screen
import com.pmb.profile.presentaion.profile.ProfileScreen


sealed class ProfileScreens(route: String, arguments: Map<String, String> = emptyMap()) :
    Screen(route = route, arguments = arguments) {
    data object Profile : ProfileScreens(route = "profile")

    companion object {
        fun fromRoute(route: String?): ProfileScreens? = when (route) {
            Profile.route -> Profile
            else -> null
        }
    }
}

    fun NavGraphBuilder.profileScreensHandle(navigationManager: NavigationManager) {
        composable(route = ProfileScreens.Profile.route) {
            ProfileScreen(navigationManager = navigationManager)
        }
    }