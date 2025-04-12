package com.pmb.transfer.presentation.transfer_select_favorite.viewmodel

import com.pmb.core.platform.BaseViewEvent
import com.pmb.transfer.domain.entity.TransactionClientBankEntity

sealed interface TransferSelectFavoriteViewEvents : BaseViewEvent {
    data class TransferDestinationAmount(val item: TransactionClientBankEntity) :
        TransferSelectFavoriteViewEvents
}