package com.pmb.account.presentation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.pmb.account.presentation.account.AccountScreen
import com.pmb.core.presentation.NavigationManager
import com.pmb.core.presentation.Screen


sealed class AccountScreens(route: String, arguments: Map<String, String> = emptyMap()) :
    Screen(route = route, arguments = arguments) {
    data object Account : AccountScreens(route = "account")

    companion object {
        fun fromRoute(route: String?): AccountScreens? =
            when (route) {
                Account.route -> Account
                else -> null
            }
    }
}

fun NavGraphBuilder.accountScreensHandle(navigationManager: NavigationManager) {
    composable(route = AccountScreens.Account.route) {
        AccountScreen(/*navigationManager = navigationManager*/)
    }
}



