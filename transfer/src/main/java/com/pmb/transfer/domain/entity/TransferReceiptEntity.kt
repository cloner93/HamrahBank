package com.pmb.transfer.domain.entity

import com.pmb.core.platform.DomainModel

data class TransferReceiptEntity(
    val account: TransactionClientBankEntity,
    val status: ReceiptStatus,
    val amount: Double,
    val date: Long,
    val senderName: String,
    val paymentType: PaymentType,
    val sourceAccount: String? = null,
    val trackingNumber: Long? = null,
    val message: String? = null
) : DomainModel
