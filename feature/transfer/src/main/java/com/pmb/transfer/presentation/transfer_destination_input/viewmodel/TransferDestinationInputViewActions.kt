package com.pmb.transfer.presentation.transfer_destination_input.viewmodel

import com.pmb.core.platform.BaseViewAction

sealed interface TransferDestinationInputViewActions : BaseViewAction {
    data object CheckAccount : TransferDestinationInputViewActions
    data object ClearAlert : TransferDestinationInputViewActions
    data class UpdateIdentifierNumber(val value: String) : TransferDestinationInputViewActions
    data class ReadTextFromClipboard(val value: String) : TransferDestinationInputViewActions
    data object ClickedOnClipboard : TransferDestinationInputViewActions

}