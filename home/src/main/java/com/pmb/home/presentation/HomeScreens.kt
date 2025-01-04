package com.pmb.home.presentation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.pmb.core.presentation.NavigationManager
import com.pmb.core.presentation.Screen
import com.pmb.home.presentation.home.HomeScreen

sealed class HomeScreens(route: String, arguments: Map<String, String> = emptyMap()) :
    Screen(route = route, arguments = arguments) {
    data object Home : HomeScreens(route = "home")

    companion object {
        fun fromRoute(route: String?): HomeScreens? = when (route) {
            Home.route -> Home
            else -> null
        }
    }
}

fun NavGraphBuilder.homeScreensHandle(navigationManager: NavigationManager) {
    composable(route = HomeScreens.Home.route) {
        HomeScreen(navigationManager = navigationManager)
    }
}