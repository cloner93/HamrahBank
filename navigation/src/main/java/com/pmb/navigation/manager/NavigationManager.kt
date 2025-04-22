package com.pmb.navigation.manager

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import com.pmb.navigation.provider.DefaultStartDestinationProvider
import com.pmb.navigation.provider.NavigationStartDestinationProvider
import com.pmb.navigation.screen.Screen
import kotlinx.coroutines.flow.StateFlow

class NavigationManager(
    val navController: NavHostController,
) {
    private val startDestinationProvider: NavigationStartDestinationProvider =
        DefaultStartDestinationProvider

    fun getStartDestination(): Screen = startDestinationProvider.getStartDestination()

    fun navigate(screen: Screen, builder: NavOptionsBuilder.() -> Unit = {}) {
        val targetRoute = screen.route
        if (navController.currentDestination?.route != targetRoute) {
            navController.navigate(targetRoute, builder)
        }
    }

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

    fun <T> setCurrentScreenData(key: String, value: T) {
        navController.currentBackStackEntry?.savedStateHandle?.set(key, value)
    }

    fun <T> getCurrentScreenFlowData(key: String, value: T): StateFlow<T>? =
        navController.currentBackStackEntry?.savedStateHandle?.getStateFlow(key, value)

    fun <T> getPreviousScreenData(key: String): T? =
        navController.previousBackStackEntry?.savedStateHandle?.get<T>(key)

    fun <T> setDestinationScreenData(screen: Screen, key: String, value: T) {
        navController.getBackStackEntry(screen.route).savedStateHandle.set(key, value)
    }

    @Composable
    inline fun <reified T : ViewModel> retrieveSharedViewModel(
        screen: Screen,
        navBackStackEntry: NavBackStackEntry
    ): T {
        val parentEntry = remember(navBackStackEntry) {
            navController.getBackStackEntry(screen.route)
        }
        return hiltViewModel(parentEntry)
    }

    fun navigateAndClearStack(
        screen: Screen,
        popUpTo: Screen = screen,
        launchSingleTop: Boolean = false
    ) {
        navController.navigate(screen.route) {
            popUpTo(popUpTo.route) {
                inclusive = true
            }
            this.launchSingleTop = launchSingleTop
        }
    }


    fun navigateWithDeepLink(deepLink: Uri) {
        navController.navigate(deepLink.toString())
    }

    fun currentRoute(): String? = navController.currentDestination?.route
}