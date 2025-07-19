package com.pmb.domain.model.openAccount.branchName

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class FetchBranchListRequest(
    val mergeStatus: Int,
    val stateCode: Int,
    val cityCode: Int,
    val organizationType: String
)
