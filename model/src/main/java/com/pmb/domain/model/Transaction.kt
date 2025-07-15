package com.pmb.domain.model

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class TransactionResponse(
    val accountRowNumber: Long,
    val balance: Long,
    val branchCode: Long,
    val transactionDate: Long,
    val payerId: Long,
    val payerName: String,
    val time: Long,
    val transactionNumber: Long,
    val creditAmount: Long,
    val debitAmount: Long,
    val transactionDescription: String,
    val branchName: String,
    val mediaTypeDescription: String,
    val documentSerial: Long
)