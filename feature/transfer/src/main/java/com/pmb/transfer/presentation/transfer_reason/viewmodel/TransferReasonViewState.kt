package com.pmb.transfer.presentation.transfer_reason.viewmodel

import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewState
import com.pmb.transfer.domain.entity.ReasonEntity

data class TransferReasonViewState(
    val loading: Boolean = false,
    val alertState: AlertModelState? = null,
    val selectedReason: ReasonEntity? = null,
    val reasons: List<ReasonEntity> = emptyList(),
) : BaseViewState
