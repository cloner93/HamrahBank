package com.pmb.transfer.presentation.transfer_search_history.viewmodel

import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewState
import com.pmb.transfer.domain.entity.TransactionClientBankEntity

data class TransferSearchHistoryViewState(
    val loading: Boolean = false,
    val alertState: AlertModelState? = null,
    val accounts: List<TransactionClientBankEntity> = emptyList(),
) : BaseViewState
