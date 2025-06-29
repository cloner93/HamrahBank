package com.pmb.domain.model.dto

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class LoginRequest(
    val customerId: String,
    val username: String,
    val password: String
)
