package com.pmb.transfer.domain.entity

import com.pmb.core.platform.DomainModel

data class AccountBankEntity(
    val id: Long? = null,
    val accountNumber: String? = null,
    val accountHolderName: String? = null,
    val accountType: String? = null,
    val accountHint: String? = null,
    val accountBalance: Double = 0.0,
    val accountStatus: AccountStatus = AccountStatus.ACTIVE,
    val defaulted: Boolean = false
) : DomainModel