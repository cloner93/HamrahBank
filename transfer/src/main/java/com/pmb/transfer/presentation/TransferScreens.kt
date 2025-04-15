package com.pmb.transfer.presentation

import android.annotation.SuppressLint
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.pmb.core.presentation.NavigationManager
import com.pmb.core.presentation.Screen
import com.pmb.transfer.presentation.transfer.TransferScreen
import com.pmb.transfer.presentation.transfer.viewmodel.TransferViewModel
import com.pmb.transfer.presentation.transfer_amount.TransferAmountScreen
import com.pmb.transfer.presentation.transfer_amount.viewmodel.TransferAmountViewModel
import com.pmb.transfer.presentation.transfer_confirm.TransferConfirmScreen
import com.pmb.transfer.presentation.transfer_confirm.viewmodel.TransferConfirmViewModel
import com.pmb.transfer.presentation.transfer_confirm_otp.TransferConfirmOtpScreen
import com.pmb.transfer.presentation.transfer_confirm_otp.viewmodel.TransferConfirmOtpViewModel
import com.pmb.transfer.presentation.transfer_destination_input.DestinationInputScreen
import com.pmb.transfer.presentation.transfer_destination_input.viewmodel.TransferDestinationInputViewModel
import com.pmb.transfer.presentation.transfer_edit_destination.TransferEditDestinationScreen
import com.pmb.transfer.presentation.transfer_edit_destination.viewmodel.TransferEditDestinationViewModel
import com.pmb.transfer.presentation.transfer_edit_favorite.TransferEditFavoriteScreen
import com.pmb.transfer.presentation.transfer_edit_favorite.viewmodel.TransferEditFavoriteViewModel
import com.pmb.transfer.presentation.transfer_method.TransferMethodScreen
import com.pmb.transfer.presentation.transfer_method.viewmodel.TransferMethodViewModel
import com.pmb.transfer.presentation.transfer_reason.TransferReasonScreen
import com.pmb.transfer.presentation.transfer_receipt.TransferReceiptScreen
import com.pmb.transfer.presentation.transfer_search_history.TransferSearchHistoryScreen
import com.pmb.transfer.presentation.transfer_search_history.viewmodel.TransferSearchHistoryViewModel
import com.pmb.transfer.presentation.transfer_select_favorite.TransferSelectFavoriteScreen
import com.pmb.transfer.presentation.transfer_select_favorite.viewmodel.TransferSelectFavoriteViewModel

sealed class TransferScreens(route: String, arguments: Map<String, String> = emptyMap()) :
    Screen(route = route, arguments = arguments) {
    data object TransferGraph : TransferScreens(route = "transfer_graph")
    data object Transfer : TransferScreens(route = "transfer")
    data object TransferDestinationSearch : TransferScreens(route = "transfer_destination_search")
    data object TransferDestinationInput : TransferScreens(route = "transfer_destination_input")
    data object TransferSelectFavorite : TransferScreens(route = "transfer_select_favorite")
    data object TransferEditLatestDestination :
        TransferScreens(route = "transfer_edit_latest_destination")

    data object TransferEditFavorite : TransferScreens(route = "transfer_edit_favorite")
    data object TransferAmount : TransferScreens(route = "transfer_amount")
    data object TransferMethod : TransferScreens(route = "transfer_method")
    data object TransferConfirm : TransferScreens(route = "transfer_confirm")
    data object TransferReason : TransferScreens(route = "transfer_reason")
    data object TransferConfirmOtp : TransferScreens(route = "transfer_confirm_otp")
    data object TransferReceipt : TransferScreens(route = "transfer_receipt")

    companion object {
        fun fromRoute(route: String?): TransferScreens? = when (route) {
            Transfer.route -> Transfer
            else -> null
        }
    }
}


@SuppressLint("StateFlowValueCalledInComposition")
fun NavGraphBuilder.transferScreensHandle(navigationManager: NavigationManager) {
    navigation(
        route = TransferScreens.TransferGraph.route,
        startDestination = TransferScreens.Transfer.route
    ) {
        composable(route = TransferScreens.Transfer.route) {
            val sharedViewModel =
                navigationManager.retrieveSharedViewModel<TransferSharedViewModel>(
                    screen = TransferScreens.TransferGraph,
                    navBackStackEntry = it
                )

            TransferScreen(
                navigationManager = navigationManager,
                viewModel = hiltViewModel<TransferViewModel>()
            ) { account ->
                sharedViewModel.setAccount(account)
            }
        }
        composable(route = TransferScreens.TransferDestinationSearch.route) {
            val sharedViewModel =
                navigationManager.retrieveSharedViewModel<TransferSharedViewModel>(
                    screen = TransferScreens.TransferGraph,
                    navBackStackEntry = it
                )

            TransferSearchHistoryScreen(
                navigationManager = navigationManager,
                viewModel = hiltViewModel<TransferSearchHistoryViewModel>()
            ) { account ->
                sharedViewModel.setAccount(account)
            }
        }
        composable(route = TransferScreens.TransferDestinationInput.route) {
            val sharedViewModel =
                navigationManager.retrieveSharedViewModel<TransferSharedViewModel>(
                    screen = TransferScreens.TransferGraph,
                    navBackStackEntry = it
                )

            DestinationInputScreen(
                navigationManager = navigationManager,
                viewModel = hiltViewModel<TransferDestinationInputViewModel>()
            ) { account ->
                sharedViewModel.setAccount(account)
            }
        }
        composable(route = TransferScreens.TransferSelectFavorite.route) {
            val sharedViewModel =
                navigationManager.retrieveSharedViewModel<TransferSharedViewModel>(
                    screen = TransferScreens.TransferGraph,
                    navBackStackEntry = it
                )

            TransferSelectFavoriteScreen(
                navigationManager = navigationManager,
                viewModel = hiltViewModel<TransferSelectFavoriteViewModel>()
            ) { account ->
                sharedViewModel.setAccount(account)
            }
        }
        composable(route = TransferScreens.TransferEditLatestDestination.route) {
            val sharedViewModel =
                navigationManager.retrieveSharedViewModel<TransferSharedViewModel>(
                    screen = TransferScreens.TransferGraph,
                    navBackStackEntry = it
                )

            TransferEditDestinationScreen(
                navigationManager = navigationManager,
                viewModel = hiltViewModel<TransferEditDestinationViewModel>()
            ) { account ->
                sharedViewModel.setAccount(account)
            }
        }
        composable(route = TransferScreens.TransferEditFavorite.route) {
            val sharedViewModel =
                navigationManager.retrieveSharedViewModel<TransferSharedViewModel>(
                    screen = TransferScreens.TransferGraph,
                    navBackStackEntry = it
                )

            TransferEditFavoriteScreen(
                navigationManager = navigationManager,
                viewModel = hiltViewModel<TransferEditFavoriteViewModel>(),
            ) { account ->
                sharedViewModel.setAccount(account)
            }
        }
        composable(route = TransferScreens.TransferAmount.route) {
            val sharedViewModel =
                navigationManager.retrieveSharedViewModel<TransferSharedViewModel>(
                    screen = TransferScreens.TransferGraph,
                    navBackStackEntry = it
                )

            TransferAmountScreen(
                navigationManager = navigationManager,
                viewModel = hiltViewModel<TransferAmountViewModel>(),
                account = sharedViewModel.account.value
            ) { amount ->
                sharedViewModel.setAmount(amount)
            }
        }
        composable(route = TransferScreens.TransferMethod.route) {
            val sharedViewModel =
                navigationManager.retrieveSharedViewModel<TransferSharedViewModel>(
                    screen = TransferScreens.TransferGraph,
                    navBackStackEntry = it
                )
            TransferMethodScreen(
                navigationManager = navigationManager,
                viewModel = hiltViewModel<TransferMethodViewModel>()
            ) { transferMethod ->
                sharedViewModel.setTransferMethod(transferMethod)
            }
        }
        composable(route = TransferScreens.TransferConfirm.route) {
            val sharedViewModel =
                navigationManager.retrieveSharedViewModel<TransferSharedViewModel>(
                    screen = TransferScreens.TransferGraph,
                    navBackStackEntry = it
                )
            TransferConfirmScreen(
                navigationManager = navigationManager,
                viewModel = hiltViewModel<TransferConfirmViewModel>(),
                account = sharedViewModel.account.value,
                amount = sharedViewModel.amount.value,
                reason = sharedViewModel.transferReason.value,
                transferMethod = sharedViewModel.transferMethod.value,
                clear = { sharedViewModel.clearPaymentData() }
            ) { sourceCardBank, sourceAccountBank, transferConfirm ->
                sharedViewModel.apply {
                    setSourceCardBank(sourceCardBank)
                    setSourceAccountBank(sourceAccountBank)
                    setTransferConfirm(transferConfirm)
                }
            }
        }
        composable(route = TransferScreens.TransferConfirmOtp.route) {
            val sharedViewModel =
                navigationManager.retrieveSharedViewModel<TransferSharedViewModel>(
                    screen = TransferScreens.TransferGraph,
                    navBackStackEntry = it
                )
            TransferConfirmOtpScreen(
                navigationManager = navigationManager,
                viewModel = hiltViewModel<TransferConfirmOtpViewModel>(),
                transferConfirm = sharedViewModel.transferConfirm.value,
                cardBank = sharedViewModel.sourceCardBank.value,
            ) { receipt ->
                sharedViewModel.setTransferReceipt(receipt)
            }
        }
        composable(route = TransferScreens.TransferReceipt.route) {
            val sharedViewModel =
                navigationManager.retrieveSharedViewModel<TransferSharedViewModel>(
                    screen = TransferScreens.TransferGraph,
                    navBackStackEntry = it
                )

            TransferReceiptScreen(
                navigationManager = navigationManager,
                viewModel = hiltViewModel(),
                transferReceipt = sharedViewModel.transferReceipt.value
            )
        }
        composable(route = TransferScreens.TransferReason.route) {
            val sharedViewModel =
                navigationManager.retrieveSharedViewModel<TransferSharedViewModel>(
                    screen = TransferScreens.TransferGraph,
                    navBackStackEntry = it
                )

            TransferReasonScreen(
                navigationManager = navigationManager,
                viewModel = hiltViewModel(),
                selectedReason = { reason ->
                    sharedViewModel.setReason(reason)
                })
        }
    }
}