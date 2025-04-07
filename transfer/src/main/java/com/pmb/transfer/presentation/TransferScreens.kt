package com.pmb.transfer.presentation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.pmb.core.presentation.NavigationManager
import com.pmb.core.presentation.Screen
import com.pmb.transfer.presentation.transfer.TransferScreen
import com.pmb.transfer.presentation.transfer_amount.TransferAmountScreen
import com.pmb.transfer.presentation.transfer_confirm.TransferConfirmScreen
import com.pmb.transfer.presentation.transfer_destination_input.DestinationInputScreen
import com.pmb.transfer.presentation.transfer_edit_destination.TransferEditDestinationScreen
import com.pmb.transfer.presentation.transfer_edit_favorite.TransferEditFavoriteScreen
import com.pmb.transfer.presentation.transfer_method.TransferMethodScreen
import com.pmb.transfer.presentation.transfer_receipt.TransferReceiptScreen
import com.pmb.transfer.presentation.transfer_search_history.TransferSearchHistoryScreen
import com.pmb.transfer.presentation.transfer_select_favorite.TransferSelectFavoriteScreen

sealed class TransferScreens(route: String, arguments: Map<String, String> = emptyMap()) :
    Screen(route = route, arguments = arguments) {
    data object Transfer : TransferScreens(route = "transfer")
    data object TransferDestinationSearch : TransferScreens(route = "transfer_destination_search")
    data object TransferDestinationInput : TransferScreens(route = "transfer_destination_input")
    data object TransferSelectFavorite : TransferScreens(route = "transfer_select_favorite")
    data object TransferEditLatestDestination : TransferScreens(route = "transfer_edit_latest_destination")
    data object TransferEditFavorite : TransferScreens(route = "transfer_edit_favorite")
    data object TransferAmount : TransferScreens(route = "transfer_amount")
    data object TransferMethod : TransferScreens(route = "transfer_method")
    data object TransferConfirm : TransferScreens(route = "transfer_confirm")
    data object TransferReceipt : TransferScreens(route = "transfer_receipt")

    companion object {
        fun fromRoute(route: String?): TransferScreens? = when (route) {
            Transfer.route -> Transfer
            else -> null
        }
    }
}


fun NavGraphBuilder.transferScreensHandle(navigationManager: NavigationManager) {
    composable(route = TransferScreens.Transfer.route) {
        TransferScreen(navigationManager = navigationManager)
    }
    composable(route = TransferScreens.TransferDestinationSearch.route) {
        TransferSearchHistoryScreen(navigationManager = navigationManager)
    }
    composable(route = TransferScreens.TransferDestinationInput.route) {
        DestinationInputScreen(navigationManager = navigationManager)
    }
    composable(route = TransferScreens.TransferSelectFavorite.route) {
        TransferSelectFavoriteScreen(navigationManager = navigationManager)
    }
    composable(route = TransferScreens.TransferEditLatestDestination.route) {
        TransferEditDestinationScreen(navigationManager = navigationManager)
    }
    composable(route = TransferScreens.TransferEditFavorite.route) {
        TransferEditFavoriteScreen(navigationManager = navigationManager)
    }
    composable(route = TransferScreens.TransferAmount.route) {
        TransferAmountScreen(navigationManager = navigationManager)
    }
    composable(route = TransferScreens.TransferMethod.route) {
        TransferMethodScreen(navigationManager = navigationManager)
    }
    composable(route = TransferScreens.TransferConfirm.route) {
        TransferConfirmScreen(navigationManager = navigationManager)
    }
    composable(route = TransferScreens.TransferReceipt.route) {
        TransferReceiptScreen(navigationManager = navigationManager)
    }
}
