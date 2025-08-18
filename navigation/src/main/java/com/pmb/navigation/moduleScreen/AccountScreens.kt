package com.pmb.navigation.moduleScreen

import com.pmb.navigation.screen.Screen

sealed class AccountScreens(route: String, arguments: Map<String, String> = emptyMap()) :
    Screen(baseRoute = route, arguments = arguments) {
    data object Account : AccountScreens(route = "account")
    data object Balance : AccountScreens(route = "balance")
    data object DepositDetailsScreen : AccountScreens(route = "depositDetails/{deposit}") {
        fun createRoute(
            deposit: String,
        ) =
            "depositDetails/$deposit"
    }

    sealed class Transactions(route: String) : AccountScreens(route) {
        data object Graph : Transactions(route = "transactions_graph")

        data object AllTransactionsList : Transactions(route = "allTransactionsList")
        data object DetailedTransactionList : Transactions(route = "DetailedTransactionList")
        data object TransactionsFilter : Transactions(route = "transactionsFilter")
        data object DepositStatement : Transactions(route = "depositStatement")
        data object TransactionSearch : Transactions(route = "transactionSearch")
    }

    sealed class IssueCard(route: String) : AccountScreens(route) {
        data object Graph : IssueCard(route = "issueCardGraph")

        data object IssueCardIntroScreen :
            IssueCard(route = "issueCardIntroScreen/{accountNumber}/{cardGroup}") {
            fun createRoute(accountNumber: String, cardGroup: Int) =
                "issueCardIntroScreen/$accountNumber/$cardGroup"
        }

        data object IssueCardConfirmScreen : IssueCard(route = "issueCardConfirmScreen")
        data object IssueCardFeeScreen : IssueCard(route = "issueCardFeeScreen")
        data object SelectAddressScreen : IssueCard(route = "selectAddressScreen")
        data object SelectCardNoScreen : IssueCard(route = "selectCardNoScreen")
        data object SelectCardShemaScreen : IssueCard(route = "selectCardShemaScreen")
        data object SelectProvincePlaceScreen : IssueCard(route = "selectCardProvincePlaceScreen")
        data object SelectCityPlaceScreen : IssueCard(route = "selectCardCityPlaceScreen")
    }

    data object TransactionReceipt :
        AccountScreens(route = "transactionReceipt/{depositId}/{transactionJson}") {
        fun createRoute(depositId: String, transactionJson: String) =
            "transactionReceipt/$depositId/$transactionJson"
    }

    companion object {
        fun fromRoute(route: String?): AccountScreens? =
            when (route) {
                Account.route -> Account
                else -> null
            }
    }
}