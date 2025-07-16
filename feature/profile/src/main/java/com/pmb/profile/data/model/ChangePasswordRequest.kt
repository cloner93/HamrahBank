package com.pmb.profile.data.model

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class ChangePasswordRequest(
    val oldPassWord: String,
    val newPassword: String,
    val customerId: Long
)