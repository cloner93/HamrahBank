package com.pmb.transfer.domain.entity

import com.pmb.core.platform.DomainModel

sealed class TransferConfirmEntity : DomainModel {

    data class ReceiptConfirm(
        val receipt: TransferReceiptEntity
    ) : TransferConfirmEntity()

    data class CardVerificationRequired(
        val verificationInfo: CardVerificationEntity
    ) : TransferConfirmEntity()
}


