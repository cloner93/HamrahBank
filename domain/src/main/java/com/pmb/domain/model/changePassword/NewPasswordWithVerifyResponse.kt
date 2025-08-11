package com.pmb.domain.model.changePassword

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class NewPasswordWithVerifyResponse(
    val intCustId: Int?,
    val mobileNumber: String?
)
