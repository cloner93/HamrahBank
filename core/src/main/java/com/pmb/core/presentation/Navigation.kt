package com.pmb.core.presentation

import android.net.Uri
import androidx.navigation.NavHostController


abstract class Screen(val route: String, val arguments: Map<String, String> = emptyMap())

class NavigationManager(val navController: NavHostController, val startDestination: Screen) {

    fun navigate(screen: Screen) {
        navController.navigate(screen.route)
    }

    fun navigateBack() {
        navController.popBackStack()
    }

    fun navigateAndClearStack(screen: Screen) {
        navController.navigate(screen.route) {
            popUpTo(0) { inclusive = true }
        }
    }

    fun navigateWithDeepLink(deepLink: Uri) {
        navController.navigate(deepLink.toString())
    }

    fun currentRoute(): String? = navController.currentDestination?.route
}