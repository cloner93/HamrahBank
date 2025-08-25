package com.pmb.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class SummarizeResponse(
    val docDesc: String,
    val totalAmount: String = "0",
    val transactionList: List<TransactionResponse>
)

