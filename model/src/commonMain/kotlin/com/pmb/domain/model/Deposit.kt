package com.pmb.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Deposit(
    val accountNumber: Long,
    val accountId: Long,
    val status: Long,
    val accountType: Long,
    val accountTypeDescription: String?,
    val customerNumber: Long,
    val balance: Long,
    val remainBalance: Long,
    val lastTransactionDate: Long,
    val lockTotalAmount: Long,
    val categoryCode: Long,
    val branchCode: Long,
    val organizationName: String?,
    val ownerType: Long,
    val isCommercial: Long,
    val chequeFlag: Long,
    val type: Long,
    val profitValue: Long,
    val expireDate: Long,
    val createDate: Long,
    val serialNumber: Long,
    val shebaNo: String?
)

