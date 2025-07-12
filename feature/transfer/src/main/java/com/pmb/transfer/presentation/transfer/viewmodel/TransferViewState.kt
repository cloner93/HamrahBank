package com.pmb.transfer.presentation.transfer.viewmodel

import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewState
import com.pmb.transfer.domain.entity.TransactionClientBankEntity

data class TransferViewState(
    val loading: Boolean = false,
    val alertState: AlertModelState? = null,
    val favoriteAccounts: List<TransactionClientBankEntity> = emptyList(),
    val accounts: List<TransactionClientBankEntity> = emptyList(),
) : BaseViewState