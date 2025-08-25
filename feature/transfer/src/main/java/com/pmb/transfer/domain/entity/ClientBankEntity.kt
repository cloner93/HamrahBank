package com.pmb.transfer.domain.entity

import com.pmb.core.platform.DomainModel
import com.pmb.transfer.utils.BankUtil.formatGropedWithSeparator

data class ClientBankEntity(
    val name: String = "",
    val phoneNumber: String = "",
    val profileUrl: String = "",
    val cardNumber: String = "",
    val accountNumber: String = "",
    val iban: String = ""
) : DomainModel {
    val cardNumberFormated: String
        get() = cardNumber.formatGropedWithSeparator(separator = "  ")
    val ibanFormated: String
        get() {
            val normalizedIban = if (iban.startsWith("IR", ignoreCase = true)) iban else "IR$iban"
            return normalizedIban.formatGropedWithSeparator()
        }
}