package com.pmb.domain.model.openAccount.accountType

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class FetchAccountTypeRequest(
    val nationalCode: String,
    val mobileNo: String
)
