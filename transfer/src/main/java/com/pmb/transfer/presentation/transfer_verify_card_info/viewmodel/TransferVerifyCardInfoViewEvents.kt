package com.pmb.transfer.presentation.transfer_verify_card_info.viewmodel

import com.pmb.core.platform.BaseViewEvent
import com.pmb.transfer.domain.entity.TransferReceiptEntity

sealed interface TransferVerifyCardInfoViewEvents : BaseViewEvent {
    data class NavigateToReceipt(val receipt: TransferReceiptEntity) : TransferVerifyCardInfoViewEvents
}