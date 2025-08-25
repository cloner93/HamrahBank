package com.pmb.transfer.domain.entity

import com.pmb.core.platform.DomainModel
import java.util.Date

data class TransferReceiptEntity(
    val amount: Double = 0.0,
    val source: TransferSourceEntity? = null,
    val destination: TransactionClientBankEntity? = null,
    val status: ReceiptStatus,
    val date: Long = Date().time,
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
