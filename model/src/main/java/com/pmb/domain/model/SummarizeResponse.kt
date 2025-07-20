package com.pmb.domain.model

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class SummarizeResponse(
    val docDesc: String,
    val totalAmount: String = "0",
    val transactionList: List<TransactionResponse>
)