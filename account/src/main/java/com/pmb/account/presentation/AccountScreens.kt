package com.pmb.account.presentation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.pmb.core.presentation.NavigationManager
import com.pmb.core.presentation.Screen


sealed class AccountScreens(route: String, arguments: Map<String, String> = emptyMap()) :
    Screen(route = route, arguments = arguments) {
    data object Account : AccountScreens(route = "account")
}

fun NavGraphBuilder.accountScreensHandle(navigationManager: NavigationManager) {
    composable(route = AccountScreens.Account.route) {
//        AccountScreen(navigationManager = navigationManager)
    }
}



