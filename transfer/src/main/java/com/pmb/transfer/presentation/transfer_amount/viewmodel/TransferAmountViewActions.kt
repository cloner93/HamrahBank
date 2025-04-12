package com.pmb.transfer.presentation.transfer_amount.viewmodel

import com.pmb.core.platform.BaseViewAction

interface TransferAmountViewActions: BaseViewAction {
    data class SubmitAmount(val amount: Long) : TransferAmountViewActions
    data object ClearAlert : TransferAmountViewActions
}