package com.pmb.transfer.presentation.transfer_amount.viewmodel

import com.pmb.core.platform.BaseViewEvent
import com.pmb.transfer.domain.entity.TransferMethodEntity

interface TransferAmountViewEvents : BaseViewEvent {
    data class NavigateToDestinationType(
        val amount: Double,
        val methods: List<TransferMethodEntity>
    ) : TransferAmountViewEvents
}