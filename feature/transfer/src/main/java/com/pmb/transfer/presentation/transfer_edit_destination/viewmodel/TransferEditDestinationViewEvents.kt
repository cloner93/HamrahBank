package com.pmb.transfer.presentation.transfer_edit_destination.viewmodel

import com.pmb.core.platform.BaseViewEvent
import com.pmb.transfer.domain.entity.TransactionClientBankEntity

sealed interface TransferEditDestinationViewEvents : BaseViewEvent {
    data object NavigateUp : TransferEditDestinationViewEvents
    data class TransferDestinationAmount(val item: TransactionClientBankEntity) :
        TransferEditDestinationViewEvents
}