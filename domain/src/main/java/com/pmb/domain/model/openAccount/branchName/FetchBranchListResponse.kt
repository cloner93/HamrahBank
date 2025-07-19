package com.pmb.domain.model.openAccount.branchName

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class FetchBranchListResponse(
    val branchList: List<Branch>
)