package com.pmb.transfer.presentation.transfer_reason.viewmodel

import com.pmb.core.platform.BaseViewAction
import com.pmb.transfer.domain.entity.ReasonEntity

sealed interface TransferReasonViewActions : BaseViewAction {
    data object ClearAlert : TransferReasonViewActions
    data class SelectReason(val reason: ReasonEntity) : TransferReasonViewActions
}