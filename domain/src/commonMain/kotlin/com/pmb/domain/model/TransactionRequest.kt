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
    val offset: String? = "",
    val count: Long = 0,
    val transType: Long = 0
)
