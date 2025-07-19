package com.pmb.domain.model.openAccount.branchName

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class Branch(
    val branchCode: Int,
    val organizationType: Int,
    val branchName: String,
    val tel: String,
    val fax: String,
    val telCode: Int,
    val hajCityCode: Int,
    val hajProvinceCode: Int,
    val address: String
)
