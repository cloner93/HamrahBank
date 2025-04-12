package com.pmb.transfer.presentation.transfer_confirm.viewmodel

import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewState

data class TransferConfirmViewState(
    val loading: Boolean = false,
    val alertState: AlertModelState? = null,
) : BaseViewState