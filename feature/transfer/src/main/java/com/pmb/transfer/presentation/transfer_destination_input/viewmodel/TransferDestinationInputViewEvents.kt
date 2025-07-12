package com.pmb.transfer.presentation.transfer_destination_input.viewmodel

import com.pmb.core.platform.BaseViewEvent
import com.pmb.transfer.domain.entity.TransactionClientBankEntity

sealed interface TransferDestinationInputViewEvents : BaseViewEvent {
    data class NavigateToDestinationAmount(val value: TransactionClientBankEntity) : TransferDestinationInputViewEvents
}