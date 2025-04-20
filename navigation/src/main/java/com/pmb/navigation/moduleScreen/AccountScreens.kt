package com.pmb.navigation.moduleScreen

import com.pmb.navigation.screen.Screen

sealed class AccountScreens(route: String, arguments: Map<String, String> = emptyMap()) :
    Screen(baseRoute = route, arguments = arguments) {
    data object Account : AccountScreens(route = "account")
    data object Balance : AccountScreens(route = "balance")
    data object Transactions : AccountScreens(route = "transactions")
    data object TransactionsFilter : AccountScreens(route = "transactionsFilter")
    data object DepositStatement : AccountScreens(route = "depositStatement")
    data object TransactionSearch : AccountScreens(route = "transactionSearch/{depositId}") {
        fun createRoute(depositId: String) = "transactionSearch/$depositId"
    }

    companion object {
        fun fromRoute(route: String?): AccountScreens? =
            when (route) {
                Account.route -> Account
                else -> null
            }
    }
}