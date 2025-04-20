package com.pmb.navigation.moduleScreen

import com.pmb.navigation.screen.Screen

sealed class TransferScreens(route: String, arguments: Map<String, String> = emptyMap()) :
    Screen(baseRoute = route, arguments = arguments) {
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
    data object TransferCardVerification : TransferScreens(route = "transfer_confirm_card_bank")
    data object TransferReceipt : TransferScreens(route = "transfer_receipt")

    companion object {
        fun fromRoute(route: String?): TransferScreens? = when (route) {
            Transfer.route -> Transfer
            else -> null
        }
    }
}
