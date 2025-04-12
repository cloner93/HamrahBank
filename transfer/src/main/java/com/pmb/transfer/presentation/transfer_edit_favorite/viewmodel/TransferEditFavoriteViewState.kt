package com.pmb.transfer.presentation.transfer_edit_favorite.viewmodel

import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewState
import com.pmb.transfer.domain.entity.TransactionClientBankEntity

data class TransferEditFavoriteViewState(
    val loading: Boolean = false,
    val alertState: AlertModelState? = null,
    val accountsFavorite: List<TransactionClientBankEntity>? = null,
) : BaseViewState
