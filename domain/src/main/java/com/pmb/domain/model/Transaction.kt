package com.pmb.domain.model

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable

@Serializable
enum class TransactionType {
    DEPOSIT,
    WITHDRAWAL,
    TRANSFER,
    RECEIVE,
    FEE,
    UNKNOWN
}

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class TransactionModel(
    val transactionId: String,
    val type: TransactionType = TransactionType.UNKNOWN,
    val title: String,
    val amount: Double,
    val currency: String = "ریال",
    val incCode: String? = null,
    val date: String
)
