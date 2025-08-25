package com.pmb.domain.model.openAccount.comissionFee

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class FetchCommissionFeeResponse(
    val errorCode: Int,
    val incomeList: List<Income>,
    val stampAmount: Int,
    val stampTitle: String,
    val stampType: String,
    val totalMainIncomeAmount: Int
)