package com.pmb.transfer.presentation.transfer_select_favorite.viewmodel

import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewState
import com.pmb.transfer.domain.entity.TransactionClientBankEntity

data class TransferSelectFavoriteViewState(
    val loading: Boolean = false,
    val alertState: AlertModelState? = null,
    val accounts: List<TransactionClientBankEntity> = emptyList()
): BaseViewState
