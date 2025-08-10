package com.pmb.transfer.presentation.transfer_edit_favorite.viewmodel

import com.pmb.core.platform.BaseViewAction
import com.pmb.transfer.domain.entity.TransactionClientBankEntity

sealed interface TransferEditFavoriteViewActions : BaseViewAction {
    data class SelectAccount(val accountValue: TransactionClientBankEntity) :
        TransferEditFavoriteViewActions

    data class SelectRemoveAccount(val accountValue: TransactionClientBankEntity) :
        TransferEditFavoriteViewActions

    data class RemoveAccount(val accountValue: TransactionClientBankEntity) :
        TransferEditFavoriteViewActions

    data object ClearAlert : TransferEditFavoriteViewActions
}