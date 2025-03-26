package com.pmb.account.presentation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.pmb.account.presentation.account.AccountScreen
import com.pmb.account.presentation.balance.BalanceScreen
import com.pmb.account.presentation.transactions.TransactionsScreen
import com.pmb.account.presentation.transactions.filterScreen.TransactionFilterScreen
import com.pmb.core.presentation.NavigationManager
import com.pmb.core.presentation.Screen


sealed class AccountScreens(route: String, arguments: Map<String, String> = emptyMap()) :
    Screen(route = route, arguments = arguments) {
    data object Account : AccountScreens(route = "account")
    data object Balance : AccountScreens(route = "balance")
    data object Transactions : AccountScreens(route = "transactions")
    data object TransactionsFilter : AccountScreens(route = "transactionsFilter")

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
        AccountScreen(navigationManager = navigationManager)
    }
    composable(route = AccountScreens.Balance.route) {
        BalanceScreen(navigationManager = navigationManager)
    }
    composable(route = AccountScreens.Transactions.route) {
        TransactionsScreen(navigationManager = navigationManager)
    }
    composable(route = AccountScreens.TransactionsFilter.route) {
        TransactionFilterScreen()
    }
}



