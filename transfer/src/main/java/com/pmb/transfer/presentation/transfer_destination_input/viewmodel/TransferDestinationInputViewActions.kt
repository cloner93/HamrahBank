package com.pmb.transfer.presentation.transfer_destination_input.viewmodel

import com.pmb.core.platform.BaseViewAction

sealed interface TransferDestinationInputViewActions: BaseViewAction {
    data class CheckAccount(val accountValue: String) : TransferDestinationInputViewActions
    data object ClearAlert: TransferDestinationInputViewActions
}