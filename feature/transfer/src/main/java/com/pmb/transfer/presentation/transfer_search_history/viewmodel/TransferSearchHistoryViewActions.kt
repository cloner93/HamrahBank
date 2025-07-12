package com.pmb.transfer.presentation.transfer_search_history.viewmodel

import com.pmb.core.platform.BaseViewAction
import com.pmb.transfer.domain.entity.TransactionClientBankEntity

sealed interface TransferSearchHistoryViewActions : BaseViewAction {
    data class SearchAccounts(val value: String) : TransferSearchHistoryViewActions
    data class SelectAccount(val item: TransactionClientBankEntity) :
        TransferSearchHistoryViewActions

    data object ClearAlert : TransferSearchHistoryViewActions
}