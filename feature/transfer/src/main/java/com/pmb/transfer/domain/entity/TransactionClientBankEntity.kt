package com.pmb.transfer.domain.entity

import com.pmb.core.platform.DomainModel
import com.pmb.domain.model.favorite.FetchFavoriteAccountResponse
import com.pmb.transfer.domain.entity.BankIdentifierNumberType.*
import com.pmb.transfer.utils.detectBankIdentifierType


data class TransactionClientBankEntity(
    val clientBankEntity: ClientBankEntity,
    val type: BankIdentifierNumberType,
    val favorite: Boolean = false
) : DomainModel

fun FetchFavoriteAccountResponse.toEntity() : TransactionClientBankEntity {
    var entity = TransactionClientBankEntity(
        favorite = true,
        type = this.number.detectBankIdentifierType()?.first ?: CARD,
        clientBankEntity = ClientBankEntity(
            name = this.description,
        ),
    )
    when(this.number.detectBankIdentifierType()?.first){
        ACCOUNT -> entity = entity.copy(clientBankEntity = entity.clientBankEntity.copy(accountNumber = this.number))
        CARD -> entity = entity.copy(clientBankEntity = entity.clientBankEntity.copy(cardNumber = this.number))
        IBAN -> entity = entity.copy(clientBankEntity = entity.clientBankEntity.copy(iban = this.number))
        else -> Unit
    }
    return entity
}