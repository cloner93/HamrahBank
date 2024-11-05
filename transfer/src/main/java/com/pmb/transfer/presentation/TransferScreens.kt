package com.pmb.transfer.presentation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.pmb.core.presentation.NavigationManager
import com.pmb.core.presentation.Screen
import com.pmb.transfer.presentation.destination_input.DestinationInputScreen
import com.pmb.transfer.presentation.transfer.TransferScreen

sealed class TransferScreens(route: String, arguments: Map<String, String> = emptyMap()) :
    Screen(route = route, arguments = arguments) {
    data object Transfer : TransferScreens(route = "transfer")
    data object DestinationInput : TransferScreens(route = "destination_input")

    companion object {
        fun fromRoute(route: String?): TransferScreens? = when (route) {
            Transfer.route -> Transfer
            DestinationInput.route -> DestinationInput
            else -> null
        }
    }
}


fun NavGraphBuilder.transferScreensHandle(navigationManager: NavigationManager) {
    composable(route = TransferScreens.Transfer.route) {
        TransferScreen(navigationManager = navigationManager)
    }

    composable(route = TransferScreens.DestinationInput.route) {
        DestinationInputScreen(navigationManager = navigationManager)
    }
}
