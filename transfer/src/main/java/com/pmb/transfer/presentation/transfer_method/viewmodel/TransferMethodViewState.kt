package com.pmb.transfer.presentation.transfer_method.viewmodel

import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewState
import com.pmb.transfer.domain.entity.TransferMethodEntity

data class TransferMethodViewState(
    val loading: Boolean = false,
    val alertState: AlertModelState? = null,
    val transferMethods: List<TransferMethodEntity> = emptyList(),
) : BaseViewState
