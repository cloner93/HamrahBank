package com.pmb.transfer.presentation.transfer_reason.viewmodel

import com.pmb.core.platform.BaseViewEvent
import com.pmb.transfer.domain.entity.ReasonEntity

sealed interface TransferReasonViewEvents : BaseViewEvent {
    data class NavigateUp(val item: ReasonEntity) : TransferReasonViewEvents
}