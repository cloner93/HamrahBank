package com.pmb.transfer.presentation.transfer_method.viewmodel

import com.pmb.core.platform.BaseViewEvent
import com.pmb.transfer.domain.entity.TransferMethodEntity

sealed interface TransferMethodViewEvents : BaseViewEvent {
    data class NavigateToTransferConfirm(val item: TransferMethodEntity) : TransferMethodViewEvents
}