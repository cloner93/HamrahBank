package com.pmb.domain.model.card

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable

@Serializable
@SuppressLint("UnsafeOptInUsageError")
data class FetchCommissionForCreateCardRequest(
    val cardGroup:Int,
    val accountNumber: Int
)
@Serializable
@SuppressLint("UnsafeOptInUsageError")
data class FetchCommissionForCreateCardResponse(
    val followId:Int,
    val stampAmount: Int,
    val incomeAmount: Int,
    val commissionAmount: Int,
    val totalAmount: Int,
    val respond: Int,
)
