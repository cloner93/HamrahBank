package com.pmb.domain.model.dto

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class LoginResponse(
    val accessToken: String,
    val refreshToken: String? = null,
)
