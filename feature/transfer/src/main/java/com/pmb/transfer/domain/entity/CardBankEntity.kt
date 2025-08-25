package com.pmb.transfer.domain.entity

import com.pmb.core.platform.DomainModel
import com.pmb.transfer.utils.BankUtil.formatGropedWithSeparator

data class CardBankEntity(
    val id: Long,
    val cardNumber: String,
    val cardHolderName: String,
    val cardExpireYear: String,
    val cardExpireMonth: String,
    val cardCvv: String,
    val cardType: String,
    val cardBalance: Double,
    val cardStatus: AccountStatus,
    val defaulted: Boolean
) : DomainModel{
    val cardNumberFormated: String
        get() = cardNumber.formatGropedWithSeparator(separator = "  ")
}
