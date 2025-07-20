package com.pmb.domain.model.openAccount.comissionFee

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class FetchCommissionFeeResponse(
    val commissionFeeInfo: List<CommissionFee>
)
