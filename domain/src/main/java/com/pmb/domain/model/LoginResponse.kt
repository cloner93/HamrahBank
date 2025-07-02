package com.pmb.domain.model

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class LoginResponse(
    val isSuccess: Boolean,
    val customerId: Long,
    val desKey: String,
    val passwordX: String,
    val securePassword: String,
    val userName: String,
    val email: String?,
    val imeiNo: String,
)