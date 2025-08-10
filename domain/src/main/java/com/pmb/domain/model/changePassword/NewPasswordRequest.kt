package com.pmb.domain.model.changePassword

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class NewPasswordRequest(
    val mobileNo: String,
    val nationalCode: String,
    val newPassword: String,
    val reNewPassword: String
)
