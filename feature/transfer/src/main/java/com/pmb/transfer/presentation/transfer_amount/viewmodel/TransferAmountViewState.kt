package com.pmb.transfer.presentation.transfer_amount.viewmodel

import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewState
import com.pmb.transfer.domain.entity.ClientBankEntity

data class TransferAmountViewState(
    val loading: Boolean = false,
    val alertState: AlertModelState? = null,
    val accountInfo: ClientBankEntity? = null,
    val amount: Double = 0.0,
) : BaseViewState {
    val enableButton: Boolean
        get() = amount >= 10.0
}