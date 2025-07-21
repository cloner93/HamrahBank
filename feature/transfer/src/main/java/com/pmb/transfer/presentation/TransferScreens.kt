@file:Suppress("CAST_NEVER_SUCCEEDS")

package com.pmb.transfer.presentation

import android.annotation.SuppressLint
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.pmb.navigation.manager.navigationManager
import com.pmb.navigation.moduleScreen.TransferScreens
import com.pmb.transfer.domain.entity.CardBankEntity
import com.pmb.transfer.domain.entity.CardVerificationEntity
import com.pmb.transfer.domain.entity.TransferReceiptEntity
import com.pmb.transfer.domain.entity.TransferSourceEntity
import com.pmb.transfer.presentation.transfer.TransferScreen
import com.pmb.transfer.presentation.transfer.viewmodel.TransferViewModel
import com.pmb.transfer.presentation.transfer_amount.TransferAmountScreen
import com.pmb.transfer.presentation.transfer_amount.viewmodel.TransferAmountViewModel
import com.pmb.transfer.presentation.transfer_confirm.TransferConfirmScreen
import com.pmb.transfer.presentation.transfer_confirm.viewmodel.TransferConfirmViewModel
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
import com.pmb.transfer.presentation.transfer_verify_card_info.TransferVerifyCardInfoScreen
import com.pmb.transfer.presentation.transfer_verify_card_info.viewmodel.TransferVerifyCardInfoViewModel


@SuppressLint("StateFlowValueCalledInComposition")
fun NavGraphBuilder.transferScreensHandle() {
    navigation(
        route = TransferScreens.TransferGraph.route,
        startDestination = TransferScreens.Transfer.route
    ) {
        composable(route = TransferScreens.Transfer.route) {
            val sharedViewModel =
                it.navigationManager.retrieveSharedViewModel<TransferSharedViewModel>(
                    screen = TransferScreens.TransferGraph,
                    navBackStackEntry = it
                )

            TransferScreen(
                viewModel = hiltViewModel<TransferViewModel>()
            ) { account ->
                sharedViewModel.setDestinationAccount(account)
            }
        }
        composable(route = TransferScreens.TransferDestinationSearch.route) {
            val sharedViewModel =
                it.navigationManager.retrieveSharedViewModel<TransferSharedViewModel>(
                    screen = TransferScreens.TransferGraph,
                    navBackStackEntry = it
                )

            TransferSearchHistoryScreen(
                viewModel = hiltViewModel<TransferSearchHistoryViewModel>()
            ) { account ->
                sharedViewModel.setDestinationAccount(account)
            }
        }
        composable(route = TransferScreens.TransferDestinationInput.route) {
            val sharedViewModel =
                it.navigationManager.retrieveSharedViewModel<TransferSharedViewModel>(
                    screen = TransferScreens.TransferGraph,
                    navBackStackEntry = it
                )

            DestinationInputScreen(
                viewModel = hiltViewModel<TransferDestinationInputViewModel>()
            ) { account ->
                sharedViewModel.setDestinationAccount(account)
            }
        }
        composable(route = TransferScreens.TransferSelectFavorite.route) {
            val sharedViewModel =
                it.navigationManager.retrieveSharedViewModel<TransferSharedViewModel>(
                    screen = TransferScreens.TransferGraph,
                    navBackStackEntry = it
                )

            TransferSelectFavoriteScreen(
                viewModel = hiltViewModel<TransferSelectFavoriteViewModel>()
            ) { account ->
                sharedViewModel.setDestinationAccount(account)
            }
        }
        composable(route = TransferScreens.TransferEditLatestDestination.route) {
            val sharedViewModel =
                it.navigationManager.retrieveSharedViewModel<TransferSharedViewModel>(
                    screen = TransferScreens.TransferGraph,
                    navBackStackEntry = it
                )

            TransferEditDestinationScreen(
                viewModel = hiltViewModel<TransferEditDestinationViewModel>()
            ) { account ->
                sharedViewModel.setDestinationAccount(account)
            }
        }
        composable(route = TransferScreens.TransferEditFavorite.route) {
            val sharedViewModel =
                it.navigationManager.retrieveSharedViewModel<TransferSharedViewModel>(
                    screen = TransferScreens.TransferGraph,
                    navBackStackEntry = it
                )

            TransferEditFavoriteScreen(
                viewModel = hiltViewModel<TransferEditFavoriteViewModel>(),
            ) { account ->
                sharedViewModel.setDestinationAccount(account)
            }
        }
        composable(route = TransferScreens.TransferAmount.route) {
            val sharedViewModel =
                it.navigationManager.retrieveSharedViewModel<TransferSharedViewModel>(
                    screen = TransferScreens.TransferGraph,
                    navBackStackEntry = it
                )

            TransferAmountScreen(
                viewModel = hiltViewModel<TransferAmountViewModel>(),
                account = sharedViewModel.destinationAccount.value
            ) { amount, methods ->
                sharedViewModel.setAmount(amount)
                sharedViewModel.setTransferMethods(methods)
            }
        }
        composable(route = TransferScreens.TransferMethod.route) {
            val sharedViewModel =
                it.navigationManager.retrieveSharedViewModel<TransferSharedViewModel>(
                    screen = TransferScreens.TransferGraph,
                    navBackStackEntry = it
                )
            TransferMethodScreen(
                viewModel = hiltViewModel<TransferMethodViewModel>(),
                methods = sharedViewModel.transferMethods.value
            ) { transferMethod ->
                sharedViewModel.setTransferMethod(transferMethod)
            }
        }
        composable(route = TransferScreens.TransferConfirm.route) {
            val sharedViewModel =
                it.navigationManager.retrieveSharedViewModel<TransferSharedViewModel>(
                    screen = TransferScreens.TransferGraph,
                    navBackStackEntry = it
                )
            TransferConfirmScreen(
                viewModel = hiltViewModel<TransferConfirmViewModel>(),
                account = sharedViewModel.destinationAccount.value,
                amount = sharedViewModel.amount.value,
                reason = sharedViewModel.transferReason.value,
                transferMethod = sharedViewModel.transferMethod.value,
                clear = { sharedViewModel.clearPaymentData() }
            ) { source: TransferSourceEntity,
                receipt: TransferReceiptEntity?,
                verificationInfo: CardVerificationEntity? ->

                sharedViewModel.apply {
                    setSource(source)
                    if (receipt != null) setTransferReceipt(receipt)
                    if (verificationInfo != null) setTransferVerificationCard(verificationInfo)
                }
            }
        }
        composable(route = TransferScreens.TransferCardVerification.route) {
            val sharedViewModel =
                it.navigationManager.retrieveSharedViewModel<TransferSharedViewModel>(
                    screen = TransferScreens.TransferGraph,
                    navBackStackEntry = it
                )

            val cardBank: CardBankEntity? = when (val source = sharedViewModel.source.value) {
                is TransferSourceEntity.Card -> source.card
                else -> null
            }
            TransferVerifyCardInfoScreen(
                viewModel = hiltViewModel<TransferVerifyCardInfoViewModel>(),
                verificationInfo = sharedViewModel.cardVerification.value,
                cardBank = cardBank
            ) { receipt ->
                sharedViewModel.setTransferReceipt(receipt)
            }
        }
        composable(route = TransferScreens.TransferReceipt.route) {
            val sharedViewModel =
                it.navigationManager.retrieveSharedViewModel<TransferSharedViewModel>(
                    screen = TransferScreens.TransferGraph,
                    navBackStackEntry = it
                )

            TransferReceiptScreen(
                viewModel = hiltViewModel(),
                transferReceipt = sharedViewModel.transferReceipt.value
            ) {
                sharedViewModel.clear()
            }
        }
        composable(route = TransferScreens.TransferReason.route) {
            val sharedViewModel =
                it.navigationManager.retrieveSharedViewModel<TransferSharedViewModel>(
                    screen = TransferScreens.TransferGraph,
                    navBackStackEntry = it
                )

            TransferReasonScreen(
                viewModel = hiltViewModel(),
                selectedReason = { reason ->
                    sharedViewModel.setReason(reason)
                })
        }
    }
}