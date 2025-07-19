package com.pmb.transfer.domain.entity

import com.pmb.core.platform.DomainModel

data class AccountBankEntity(
    val id: Long,
    val accountNumber: String,
    val accountHolderName: String,
    val accountType: String,
    val accountHint: String?,
    val accountBalance: Double,
    val accountStatus: AccountStatus,
    val defaulted: Boolean
) : DomainModel