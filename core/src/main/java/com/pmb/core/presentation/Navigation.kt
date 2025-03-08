package com.pmb.core.presentation

import android.net.Uri
import androidx.navigation.NavHostController
import kotlinx.coroutines.flow.StateFlow


abstract class Screen(val route: String, val arguments: Map<String, String> = emptyMap())

class NavigationManager(val navController: NavHostController, val startDestination: Screen) {

    fun navigate(screen: Screen) {
        navController.navigate(screen.route)
    }

    fun navigateWithString(destination: String) {
        navController.navigate(destination)
    }

    fun navigateBack() {
        navController.popBackStack()
    }

    fun <T> setPreviousScreenData(key: String, value: T) {
        navController.previousBackStackEntry?.savedStateHandle?.set(key, value)
    }

    fun <T> getCurrentScreenFlowData(key: String, value: T): StateFlow<T>? =
        navController.currentBackStackEntry?.savedStateHandle?.getStateFlow(
            key, value
        )


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