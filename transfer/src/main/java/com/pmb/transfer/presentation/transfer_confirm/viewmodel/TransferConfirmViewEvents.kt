package com.pmb.transfer.presentation.transfer_confirm.viewmodel

import com.pmb.core.platform.BaseViewEvent
import com.pmb.transfer.domain.entity.CardVerificationEntity
import com.pmb.transfer.domain.entity.TransferReceiptEntity
import com.pmb.transfer.domain.entity.TransferSourceEntity

sealed interface TransferConfirmViewEvents : BaseViewEvent {

    data class NavigateToCardVerification(
        val source: TransferSourceEntity,
        val verificationInfo: CardVerificationEntity,
    ) : TransferConfirmViewEvents

    data class NavigateToReceipt(
        val source: TransferSourceEntity,
        val receipt: TransferReceiptEntity,
    ) : TransferConfirmViewEvents
}