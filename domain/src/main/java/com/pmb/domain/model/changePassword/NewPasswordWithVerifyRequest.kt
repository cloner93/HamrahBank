package com.pmb.domain.model.changePassword

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class NewPasswordWithVerifyRequest(
    val nationalCode: String, val password: String
)
