package com.pmb.transfer.domain.entity

import com.pmb.core.platform.DomainModel


data class TransactionClientBankEntity(
    val clientBankEntity: ClientBankEntity,
    val type: BankIdentifierNumberType,
    val favorite: Boolean = false
) : DomainModel