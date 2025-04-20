package com.pmb.navigation.manager

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.NavBackStackEntry

val LocalNavigationManager = staticCompositionLocalOf<NavigationManager> {
    error("NavigationManager not provided")
}

val NavBackStackEntry.navigationManager: NavigationManager
    @Composable get() = LocalNavigationManager.current
