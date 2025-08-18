package com.pmb.domain.model.card

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable


@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class BouncedChequeCustomerModel(
    val amount: Int,
    val bankCode: Int,
    val bouncedAmount: Int,
    val bouncedBranchName: String?,
    val bouncedDate: String?,
    val bouncedReason: BouncedReason,
    val branchBounced: String?,
    val branchOrigin: String?,
    val channelKind: Int,
    val customerType: Int,
    val deadlineDate: String?,
    val iban: String?,
    val idCheque: Int,
    val letterDate: String?,
    val originBranchName: String?,
    val serial: String?
)