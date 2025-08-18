package com.pmb.domain.model

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class DepositModel(
    val title: String,
    val desc: String?,
    val depositNumber: String,
    val accountId: Long,
    val accountType: Long,
    val categoryCode: Long,
    val amount: Double,
    val currency: String,
    val ibanNumber: String,
    val cardNumber: String,
    var isSelected: Boolean = false,
    val branchCode: String = "",
    val branchName: String = "",
    val profitValue: Long = 0,
    val lastTransactionDate: Long = 0,
)