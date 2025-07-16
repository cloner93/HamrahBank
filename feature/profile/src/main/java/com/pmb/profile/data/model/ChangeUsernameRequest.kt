package com.pmb.profile.data.model

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class ChangeUsernameRequest(
    val newUserName: String,
    val oldUserName: String
)