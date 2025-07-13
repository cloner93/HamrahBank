package com.pmb.domain.model

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable


@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class TransactionRequest(
    val categoryCode: Long = 0,
    val fromDate: Long = 0,
    val toDate: Long = 0,
    val extAccNo: Long = 0,
    val count: Int = 0
)


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