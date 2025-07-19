package com.pmb.domain.model.openAccount.accountVerifyCode

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class AnnualIncomeType(
    val incomePredictDescription: String,
    val incomePredictId: Int
)