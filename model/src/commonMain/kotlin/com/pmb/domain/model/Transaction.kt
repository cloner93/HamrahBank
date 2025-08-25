package com.pmb.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class TransactionResponse(
    val balance: Long,
    val branchCode: Long,
    val branchName: String?,
    val creditAmount: Long,
    val debitAmount: Long,
    val docDesc: String?,
    val documentSerial: Long,
    val incCode: String?,
    val mediaTypeDescription: String?,
    val payerId: Long,
    val payerName: String?,
    val time: String?,
    val transactionDate: Long,
    val transactionDescription: String?,
    val transactionNumber: Long
)

