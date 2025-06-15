package com.pmb.domain.model.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class LoginResponse(
    @SerialName("access_token")
    val accessToken: String,
//    @SerialName("expires_in")
//    val expiresIn: Int,
    @SerialName("refresh_token")
    val refreshToken: String? = null,
)
