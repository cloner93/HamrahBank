package com.pmb.auth.domain.activate.entity

data class ActivateEntity(
    val isSuccess: Boolean
)

data class ActivateParams(
    val userName: String,
    val mobileNumber: String,
    val password: String,
    val nationalId: String
)