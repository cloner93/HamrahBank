package com.pmb.domain.model.openAccount.accountVerifyCode

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class AnnualTransactionType(
    val incomePredictDescription: String?=null,
    val incomePredictId: Int
)