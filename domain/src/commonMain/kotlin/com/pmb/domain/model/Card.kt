package com.pmb.domain.model

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class Card(
    val cardType: Long,
    val pan: Long,
    val cardDesc: String?,
    val cardTypeDescription: String?,
    val cardGroup: Long,
    val expireDate: Long,
    val contractId: Long,
    val cardStatus: Long,
    val maxAmount: Long,
    val maxMonthlyPay: Long,
    val balance: Long,
    val accountId: Long,
    val ownerType: Long,
    val lastTransactionDate: Long,
    val ownerName: String?
)
