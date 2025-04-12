package com.pmb.transfer.presentation.transfer.viewmodel

import com.pmb.core.platform.BaseViewEvent
import com.pmb.transfer.domain.entity.TransactionClientBankEntity

sealed interface TransferViewEvents: BaseViewEvent {
    data object TransferDestination : TransferViewEvents
    data class TransferDestinationAmount(val item: TransactionClientBankEntity) : TransferViewEvents
}