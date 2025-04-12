package com.pmb.transfer.presentation.transfer_amount.viewmodel

import com.pmb.core.platform.BaseViewEvent

interface TransferAmountViewEvents: BaseViewEvent {
    data class NavigateToDestinationType(val amount: Long) : TransferAmountViewEvents
}