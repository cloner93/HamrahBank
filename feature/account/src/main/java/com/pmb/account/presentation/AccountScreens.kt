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
import com.pmb.account.presentation.issueCard.IssueCardSharedViewModel
import com.pmb.account.presentation.issueCard.issueCardConfirm.IssueCardConfirmScreen
import com.pmb.account.presentation.issueCard.issueCardConfirm.viewmodel.IssueCardConfirmViewModel
import com.pmb.account.presentation.issueCard.issueCardFee.IssueCardFeeScreen
import com.pmb.account.presentation.issueCard.issueCardIntro.IssueCardIntroScreen
import com.pmb.account.presentation.issueCard.issueCardIntro.viewModel.IssueCardIntroViewModel
import com.pmb.account.presentation.issueCard.selectAddress.SelectAddressScreen
import com.pmb.account.presentation.issueCard.selectAddress.viewModel.SelectAddressViewModel
import com.pmb.account.presentation.issueCard.selectCardNo.SelectCardNoScreen
import com.pmb.account.presentation.issueCard.selectCardNo.viewmodel.SelectCardNoViewModel
import com.pmb.account.presentation.issueCard.selectCardShema.SelectCardShemaScreen
import com.pmb.account.presentation.issueCard.selectCardShema.SelectCardShemaViewModel
import com.pmb.account.presentation.issueCard.selectCityPlace.SelectCityPlaceScreen
import com.pmb.account.presentation.issueCard.selectProvincePlace.SelectProvincePlaceScreen
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
import com.pmb.domain.model.card.CardCustomerAddressResponse
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

    // issue card
    navigation(
        route = AccountScreens.IssueCard.Graph.route,
        startDestination = AccountScreens.IssueCard.IssueCardIntroScreen.route
    ) {
        composable(
            route = AccountScreens.IssueCard.IssueCardIntroScreen.route,
            deepLinks = listOf(navDeepLink {
                uriPattern = "myapp://issueCardIntroScreen/{accountNumber}/{cardGroup}"
            }),
            arguments = listOf(
                navArgument("accountNumber") { type = NavType.StringType },
                navArgument("cardGroup") { type = NavType.LongType },
            )
        ) {

            val sharedViewModel =
                it.navigationManager.retrieveSharedViewModel<IssueCardSharedViewModel>(
                    screen = AccountScreens.IssueCard.Graph, navBackStackEntry = it
                )

            val viewmodel = hiltViewModel<IssueCardIntroViewModel>()
            IssueCardIntroScreen(
                viewmodel,
                onUpdateData = { userData: CardCustomerAddressResponse, accountNumber, cardGroup ->
                    sharedViewModel.updateState {
                        copy(
                            userData = userData,
                            accountNumber = accountNumber,
                            cardGroup = cardGroup
                        )
                    }
                }
            )
        }
        composable(route = AccountScreens.IssueCard.SelectAddressScreen.route) {

            val sharedViewModel =
                it.navigationManager.retrieveSharedViewModel<IssueCardSharedViewModel>(
                    screen = AccountScreens.IssueCard.Graph, navBackStackEntry = it
                )
            val sharedState = sharedViewModel.state.collectAsStateWithLifecycle()

            val viewmodel = hiltViewModel<SelectAddressViewModel>()

            SelectAddressScreen(
                viewmodel,
                sharedState.value,
                onUpdateProvinceList = { provinces ->
                    sharedViewModel.updateState { copy(provinceList = provinces) }
                },
                onUpdateCityList = { cityList ->
                    sharedViewModel.updateState { copy(cityList = cityList) }
                },
                onUpdateAddress = { addressType, address, postalCode ->
                    sharedViewModel.updateState {
                        copy(
                            addressType = addressType,
                            address = address,
                            postalCode = postalCode
                        )
                    }
                }
            )
        }
        composable(route = AccountScreens.IssueCard.SelectProvincePlaceScreen.route) {
            val sharedViewModel =
                it.navigationManager.retrieveSharedViewModel<IssueCardSharedViewModel>(
                    screen = AccountScreens.IssueCard.Graph, navBackStackEntry = it
                )
            val sharedState = sharedViewModel.state.collectAsStateWithLifecycle()

            SelectProvincePlaceScreen(
                sharedState.value,
                {
                    sharedViewModel.updateState {
                        copy(
                            provinceOfDeposit = it,

                            cityList = emptyList(),
                            cityOfDeposit = null
                        )
                    }
                }
            )
        }
        composable(route = AccountScreens.IssueCard.SelectCityPlaceScreen.route) {
            val sharedViewModel =
                it.navigationManager.retrieveSharedViewModel<IssueCardSharedViewModel>(
                    screen = AccountScreens.IssueCard.Graph, navBackStackEntry = it
                )
            val sharedState = sharedViewModel.state.collectAsStateWithLifecycle()

            SelectCityPlaceScreen(
                sharedState.value,
                { sharedViewModel.updateState { copy(cityOfDeposit = it) } }
            )
        }
        composable(route = AccountScreens.IssueCard.SelectCardShemaScreen.route) {

            val sharedViewModel =
                it.navigationManager.retrieveSharedViewModel<IssueCardSharedViewModel>(
                    screen = AccountScreens.IssueCard.Graph, navBackStackEntry = it
                )
            sharedViewModel.state.collectAsStateWithLifecycle()
            val viewModel = hiltViewModel<SelectCardShemaViewModel>()
            SelectCardShemaScreen(
                viewModel,
                onSelectedCard = { id ->
                    sharedViewModel.updateState { copy(formatId = id ?: 0) }
                }
            )
        }
        composable(route = AccountScreens.IssueCard.SelectCardNoScreen.route) {

            val sharedViewModel =
                it.navigationManager.retrieveSharedViewModel<IssueCardSharedViewModel>(
                    screen = AccountScreens.IssueCard.Graph, navBackStackEntry = it
                )
            val sharedState = sharedViewModel.state.collectAsStateWithLifecycle()

            val viewModel = hiltViewModel<SelectCardNoViewModel>()
            SelectCardNoScreen(
                viewmodel = viewModel,
                sharedValue = sharedState.value,
                onUpdateOwnerAccount = { cardOwnerDeposit, feeDeposit ->
                    sharedViewModel.updateState {
                        copy(
                            cardOwnerAccount = cardOwnerDeposit,
                            commissionFeeAccount = feeDeposit
                        )
                    }
                },
                onUpdateSelectedCard = { pan ->
                    sharedViewModel.updateState {
                        copy(selectedOldPan = pan)
                    }
                }
            ) { commission ->
                sharedViewModel.updateState { copy(commissionFee = commission) }
            }
        }
        composable(route = AccountScreens.IssueCard.IssueCardFeeScreen.route) {

            val sharedViewModel =
                it.navigationManager.retrieveSharedViewModel<IssueCardSharedViewModel>(
                    screen = AccountScreens.IssueCard.Graph, navBackStackEntry = it
                )
            val sharedState = sharedViewModel.state.collectAsStateWithLifecycle()
            IssueCardFeeScreen(sharedState.value.commissionFee)
        }
        composable(route = AccountScreens.IssueCard.IssueCardConfirmScreen.route) {
            val sharedViewModel =
                it.navigationManager.retrieveSharedViewModel<IssueCardSharedViewModel>(
                    screen = AccountScreens.IssueCard.Graph, navBackStackEntry = it
                )
            val sharedState = sharedViewModel.state.collectAsStateWithLifecycle()

            val viewModel = hiltViewModel<IssueCardConfirmViewModel>()
            IssueCardConfirmScreen(viewModel = viewModel, value = sharedState.value)
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