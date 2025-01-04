package com.pmb.transfer.presentation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.pmb.core.presentation.NavigationManager
import com.pmb.core.presentation.Screen
import com.pmb.transfer.presentation.amount.AmountScreen
import com.pmb.transfer.presentation.destination_input.DestinationInputScreen
import com.pmb.transfer.presentation.transfer.TransferScreen
import com.pmb.transfer.presentation.transfer_confirm.TransferConfirmScreen
import com.pmb.transfer.presentation.transfer_method.TransferMethodScreen

sealed class TransferScreens(route: String, arguments: Map<String, String> = emptyMap()) :
    Screen(route = route, arguments = arguments) {
    data object Transfer : TransferScreens(route = "transfer")
    data object Search : TransferScreens(route = "search")
    data object DestinationInput : TransferScreens(route = "destination_input")
    data object Amount : TransferScreens(route = "amount")
    data object TransferMethod : TransferScreens(route = "transfer_method")
    data object TransferConfirm : TransferScreens(route = "transfer_confirm")

    companion object {
        fun fromRoute(route: String?): TransferScreens? = when (route) {
            Transfer.route -> Transfer
            DestinationInput.route -> DestinationInput
            Amount.route -> Amount
            TransferConfirm.route -> TransferConfirm
            else -> null
        }
    }
}


fun NavGraphBuilder.transferScreensHandle(navigationManager: NavigationManager) {
    composable(route = TransferScreens.Transfer.route) {
        TransferScreen(navigationManager = navigationManager)
    }

    composable(route = TransferScreens.Search.route) {
//        DestinationInputScreen(navigationManager = navigationManager)
    }

    composable(route = TransferScreens.DestinationInput.route) {
        DestinationInputScreen(navigationManager = navigationManager)
    }

    composable(route = TransferScreens.Amount.route) {
        AmountScreen(navigationManager = navigationManager)
    }

    composable(route = TransferScreens.TransferMethod.route) {
        TransferMethodScreen(navigationManager = navigationManager)
    }

    composable(route = TransferScreens.TransferConfirm.route) {
        TransferConfirmScreen(navigationManager = navigationManager)
    }
}
