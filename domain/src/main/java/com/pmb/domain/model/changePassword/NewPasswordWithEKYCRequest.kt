package com.pmb.domain.model.changePassword

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class NewPasswordWithEKYCRequest(
    val admittanceText: String,
    val cardSerialNo: String,
    val mobileNumber: String,
    val nationalCode: String,
    val authImage: String,
    val authVideo: String
)
