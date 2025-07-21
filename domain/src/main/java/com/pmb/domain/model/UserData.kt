package com.pmb.domain.model

data class UserData(
    val customerId: String,
    val username: String,
    val firstName: String,
    val lastName: String,
    val phoneNumber: String,

    ) {
    val fullName: String
        get() = "$firstName $lastName"
}