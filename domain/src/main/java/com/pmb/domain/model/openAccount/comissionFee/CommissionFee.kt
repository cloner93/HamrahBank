package com.pmb.domain.model.openAccount.comissionFee

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class CommissionFee(
    val desc: String,
    val code: Int,
    val amount: Long,
    val optional: Boolean
)
