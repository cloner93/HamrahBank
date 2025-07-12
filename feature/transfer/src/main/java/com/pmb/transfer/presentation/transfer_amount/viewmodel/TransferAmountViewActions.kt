package com.pmb.transfer.presentation.transfer_amount.viewmodel

import com.pmb.core.platform.BaseViewAction

interface TransferAmountViewActions: BaseViewAction {
    data class UpdateAmount(val amount: Double) : TransferAmountViewActions
    data object SubmitAmount : TransferAmountViewActions
    data object ClearAlert : TransferAmountViewActions
}