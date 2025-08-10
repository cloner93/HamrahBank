package com.pmb.domain.model

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class UserData(
    val customerId: String,
    val username: String,
    val firstName: String,
    val lastName: String,
    val phoneNumber: String,
    val password: String,
    ) {
    val fullName: String
        get() = "$firstName $lastName"
}