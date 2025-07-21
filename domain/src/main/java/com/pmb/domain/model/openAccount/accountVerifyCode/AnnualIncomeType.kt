package com.pmb.domain.model.openAccount.accountVerifyCode

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class AnnualIncomeType(
    val incomePredictDescription: String?=null,
    val incomePredictId: Int
)