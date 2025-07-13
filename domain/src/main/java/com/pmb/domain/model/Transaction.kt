package com.pmb.domain.model


enum class TransactionType {
    DEPOSIT,
    WITHDRAWAL,
    TRANSFER,
    RECEIVE,
    FEE,
    UNKNOWN
}


data class TransactionModel(
    val transactionId: String,
    val type: TransactionType = TransactionType.UNKNOWN,
    val title: String = "نامشخص",
    val amount: Double,
    val currency: String = "ریال",
    val date: String
)
