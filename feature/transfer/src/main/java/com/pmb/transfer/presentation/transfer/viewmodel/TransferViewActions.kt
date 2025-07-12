package com.pmb.transfer.presentation.transfer.viewmodel

import com.pmb.core.platform.BaseViewAction
import com.pmb.transfer.domain.entity.TransactionClientBankEntity

sealed interface TransferViewActions : BaseViewAction {
    data class SelectAccount(val item: TransactionClientBankEntity) : TransferViewActions
    data object NavigateToDestinationInput : TransferViewActions
    data object ClearAlert : TransferViewActions
}