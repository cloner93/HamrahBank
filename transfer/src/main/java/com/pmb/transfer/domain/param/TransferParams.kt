package com.pmb.transfer.domain.param

import com.pmb.transfer.domain.entity.BankIdentifierNumberType
import com.pmb.transfer.domain.entity.TransactionClientBankEntity

data class AccountDetailParam(val value: String, val type: BankIdentifierNumberType)

data class AccountRemoveFavoriteParam(val item: TransactionClientBankEntity)

data class AccountFavoriteToggleParam(val newStatus: Boolean, val item: TransactionClientBankEntity)