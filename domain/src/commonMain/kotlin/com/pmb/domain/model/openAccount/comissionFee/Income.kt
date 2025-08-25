package com.pmb.domain.model.openAccount.comissionFee

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class Income(
    val incomeId: Int,
    val incomeName: String,
    val mainIncomeAmount: Int,
    val usedType: Int
)