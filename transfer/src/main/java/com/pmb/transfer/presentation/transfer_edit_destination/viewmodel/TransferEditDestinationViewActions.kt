package com.pmb.transfer.presentation.transfer_edit_destination.viewmodel

import com.pmb.core.platform.BaseViewAction
import com.pmb.transfer.domain.entity.TransactionClientBankEntity

sealed interface TransferEditDestinationViewActions : BaseViewAction {
    data class ChangeFavoriteStatus(val newStatus: Boolean, val item: TransactionClientBankEntity) :
        TransferEditDestinationViewActions
    data class SelectAccount(val item: TransactionClientBankEntity) :
        TransferEditDestinationViewActions
    data object ClearAlert : TransferEditDestinationViewActions
}