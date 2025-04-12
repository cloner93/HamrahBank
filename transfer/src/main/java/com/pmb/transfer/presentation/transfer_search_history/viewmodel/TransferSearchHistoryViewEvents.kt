package com.pmb.transfer.presentation.transfer_search_history.viewmodel

import com.pmb.core.platform.BaseViewEvent
import com.pmb.transfer.domain.entity.TransactionClientBankEntity

sealed interface TransferSearchHistoryViewEvents : BaseViewEvent {
    data class TransferDestinationAmount(val item: TransactionClientBankEntity) :
        TransferSearchHistoryViewEvents
}