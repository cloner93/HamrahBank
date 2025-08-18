package com.pmb.domain.model.card

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable

@Serializable
@SuppressLint("UnsafeOptInUsageError")
data class FetchCommissionForCreateCardRequest(
    val cardGroup: Long,
    val accountNumber: Long
)
@Serializable
@SuppressLint("UnsafeOptInUsageError")
data class FetchCommissionForCreateCardResponse(
    val followId: Long,
    val stampAmount: Long,
    val incomeAmount: Long,
    val commissionAmount: Long,
    val totalAmount: Long,
    val respond: Long,
    val incomeType: Long
)
