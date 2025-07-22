package com.pmb.transfer.presentation.transfer_amount.viewmodel

import com.pmb.core.platform.BaseViewAction
import com.pmb.transfer.domain.entity.TransactionClientBankEntity

sealed interface TransferAmountViewActions: BaseViewAction {
    data class UpdateAmount(val amount: Double) : TransferAmountViewActions
    data class UpdateDestination(val account: TransactionClientBankEntity?) : TransferAmountViewActions
    data object SubmitAmount : TransferAmountViewActions
    data object ClearAlert : TransferAmountViewActions
}