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
