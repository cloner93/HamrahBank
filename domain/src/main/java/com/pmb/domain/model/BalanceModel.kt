package com.pmb.domain.model

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable


@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class BalanceModel(
    val balance: Long,
    val lockTotalAmount: Long,
    val remainBalance: Long
)
