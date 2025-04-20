package com.pmb.account.presentation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.pmb.account.presentation.account.AccountScreen
import com.pmb.account.presentation.balance.BalanceScreen
import com.pmb.account.presentation.transactions.TransactionsScreen
import com.pmb.account.presentation.transactions.filterScreen.TransactionFilterScreen
import com.pmb.account.presentation.transactions.search.TransactionSearchScreen
import com.pmb.account.presentation.transactions.statement.DepositStatementScreen
import com.pmb.navigation.moduleScreen.AccountScreens


fun NavGraphBuilder.accountScreensHandle() {
    composable(route = AccountScreens.Account.route) {
        AccountScreen()
    }
    composable(route = AccountScreens.Balance.route) {
        BalanceScreen()
    }
    composable(route = AccountScreens.Transactions.route) {
        TransactionsScreen()
    }
    composable(route = AccountScreens.TransactionsFilter.route) {
        TransactionFilterScreen()
    }
    composable(route = AccountScreens.DepositStatement.route) {
        DepositStatementScreen()
    }
    composable(
        route = AccountScreens.TransactionSearch.route,
        deepLinks = listOf(navDeepLink {
            uriPattern = "myapp://transactionSearch/{depositId}"
        }),
        arguments = listOf(navArgument("depositId") { type = NavType.StringType })
    ) {
        TransactionSearchScreen()
    }
}