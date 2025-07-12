package com.pmb.transfer.presentation.transfer_edit_favorite.viewmodel

import com.pmb.core.platform.BaseViewEvent
import com.pmb.transfer.domain.entity.TransactionClientBankEntity

sealed interface TransferEditFavoriteViewEvents : BaseViewEvent {
    data class NavigateToDestinationAmount(val item: TransactionClientBankEntity) : TransferEditFavoriteViewEvents
}