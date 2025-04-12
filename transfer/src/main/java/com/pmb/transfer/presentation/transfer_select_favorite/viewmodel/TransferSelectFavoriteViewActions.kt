package com.pmb.transfer.presentation.transfer_select_favorite.viewmodel

import com.pmb.core.platform.BaseViewAction
import com.pmb.transfer.domain.entity.TransactionClientBankEntity

sealed interface TransferSelectFavoriteViewActions: BaseViewAction {
    data object ClearAlert: TransferSelectFavoriteViewActions
    data class SelectFavorite(val item: TransactionClientBankEntity): TransferSelectFavoriteViewActions
}