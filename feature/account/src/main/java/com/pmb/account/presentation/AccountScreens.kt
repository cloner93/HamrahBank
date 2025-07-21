package com.pmb.account.presentation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import androidx.navigation.navigation
import com.pmb.account.presentation.account.AccountScreen
import com.pmb.account.presentation.account.details.DepositDetailsScreen
import com.pmb.account.presentation.account.details.DepositDetailsViewModel
import com.pmb.account.presentation.balance.BalanceScreen
import com.pmb.account.presentation.transactionReceipt.TransactionsReceiptScreen
import com.pmb.account.presentation.transactions.DetailedTransactionList
import com.pmb.account.presentation.transactions.TransactionSharedViewModel
import com.pmb.account.presentation.transactions.TransactionsScreen
import com.pmb.account.presentation.transactions.detailedTransactionLIst.DetailedTransactionListViewModel
import com.pmb.account.presentation.transactions.filterScreen.TransactionFilterScreen
import com.pmb.account.presentation.transactions.filterScreen.viewmodel.TransactionsFilterViewModel
import com.pmb.account.presentation.transactions.search.TransactionSearchScreen
import com.pmb.account.presentation.transactions.search.viewmodel.TransactionSearchViewModel
import com.pmb.account.presentation.transactions.statement.DepositStatementScreen
import com.pmb.account.presentation.transactions.statement.viewmodel.DepositStatementViewModel
import com.pmb.account.presentation.transactions.viewmodel.TransactionsViewModel
import com.pmb.navigation.manager.navigationManager
import com.pmb.navigation.moduleScreen.AccountScreens

fun NavGraphBuilder.accountScreensHandle() {
    composable(route = AccountScreens.Account.route) {
        AccountScreen()
    }
    composable(route = AccountScreens.Balance.route) {
        BalanceScreen()
    }
    composable(
        route = AccountScreens.DepositDetailsScreen.route,
        deepLinks = listOf(navDeepLink {
            uriPattern = "myapp://depositDetails/{deposit}"
        }),
        arguments = listOf(
            navArgument("deposit") { type = NavType.StringType },
        )
    ) {
        DepositDetailsScreen(viewModel = hiltViewModel<DepositDetailsViewModel>())
    }

    navigation(
        route = AccountScreens.Transactions.Graph.route,
        startDestination = AccountScreens.Transactions.AllTransactionsList.route
    ) {
        composable(route = AccountScreens.Transactions.AllTransactionsList.route) {
            val sharedViewModel =
                it.navigationManager.retrieveSharedViewModel<TransactionSharedViewModel>(
                    screen = AccountScreens.Transactions.Graph, navBackStackEntry = it
                )
            val sharedState = sharedViewModel.state.collectAsStateWithLifecycle()

            TransactionsScreen(
                viewModel = hiltViewModel<TransactionsViewModel>(),
                sharedState = sharedState.value,
                updateSummarize = { summarize, depositModel ->
                    depositModel?.let { deposit ->
                        sharedViewModel.updateState {
                            copy(summarize = summarize, selectedDeposit = deposit)
                        }
                    }
                },
                updateListOfTransaction = { list, depositModel ->
                    depositModel?.let { deposit ->
                        sharedViewModel.updateState {
                            copy(transactionList = list, selectedDeposit = deposit)
                        }
                    }
                }
            )
        }

        composable(route = AccountScreens.Transactions.TransactionsFilter.route) {
            val sharedViewModel =
                it.navigationManager.retrieveSharedViewModel<TransactionSharedViewModel>(
                    screen = AccountScreens.Transactions.Graph, navBackStackEntry = it
                )
            val sharedState = sharedViewModel.state.collectAsStateWithLifecycle()

            TransactionFilterScreen(
                viewModel = hiltViewModel<TransactionsFilterViewModel>(),
                sharedState = sharedState.value
            ) { transactionId ->

            }
        }
        composable(route = AccountScreens.Transactions.DepositStatement.route) {
            val sharedViewModel =
                it.navigationManager.retrieveSharedViewModel<TransactionSharedViewModel>(
                    screen = AccountScreens.Transactions.Graph, navBackStackEntry = it
                )
            val sharedState = sharedViewModel.state.collectAsStateWithLifecycle()
            DepositStatementScreen(
                viewModel = hiltViewModel<DepositStatementViewModel>(),
                sharedState = sharedState.value
            )
        }
        composable(route = AccountScreens.Transactions.DetailedTransactionList.route) {
            val sharedViewModel =
                it.navigationManager.retrieveSharedViewModel<TransactionSharedViewModel>(
                    screen = AccountScreens.Transactions.Graph, navBackStackEntry = it
                )
            val sharedState = sharedViewModel.state.collectAsStateWithLifecycle()
            DetailedTransactionList(
                viewModel = hiltViewModel<DetailedTransactionListViewModel>(),
                sharedState = sharedState.value
            )
        }

        composable(route = AccountScreens.Transactions.TransactionSearch.route) {
            val sharedViewModel =
                it.navigationManager.retrieveSharedViewModel<TransactionSharedViewModel>(
                    screen = AccountScreens.Transactions.Graph, navBackStackEntry = it
                )
            val sharedState = sharedViewModel.state.collectAsStateWithLifecycle()

            TransactionSearchScreen(
                viewModel = hiltViewModel<TransactionSearchViewModel>(),
                sharedState = sharedState.value,
            )

        }
    }

    composable(
        route = AccountScreens.TransactionReceipt.route,
        deepLinks = listOf(navDeepLink {
            uriPattern = "myapp://transactionReceipt/{depositId}/{transactionJson}"
        }),
        arguments = listOf(
            navArgument("depositId") { type = NavType.StringType },
            navArgument("transactionJson") { type = NavType.StringType }
        )
    ) {
        TransactionsReceiptScreen()
    }
}