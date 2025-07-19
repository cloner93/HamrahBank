package com.pmb.domain.model.openAccount.accountType

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class FetchAccountTypeRequest(
    val customerType: Int,
    val nationalCode: String,
    val mobileNo: String
)
