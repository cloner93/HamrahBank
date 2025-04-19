package com.pmb.transfer.domain.entity

import com.pmb.core.platform.DomainModel

data class TransferReceiptEntity(
    val amount: Double,
    val source: TransferSourceEntity,
    val destination: TransactionClientBankEntity,
    val status: ReceiptStatus,
    val date: Long,
    val paymentType: PaymentType,
    val trackingNumber: Long,
    val message: String? = null
) : DomainModel

sealed class TransferSourceEntity : DomainModel {
    data class Card(val card: CardBankEntity) : TransferSourceEntity()
    data class Account(val account: AccountBankEntity) : TransferSourceEntity()
}

fun TransferSourceEntity?.asCard(): CardBankEntity? =
    (this as? TransferSourceEntity.Card)?.card

fun TransferSourceEntity?.asAccount(): AccountBankEntity? =
    (this as? TransferSourceEntity.Account)?.account
