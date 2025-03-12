package com.pmb.auth.domain.activate.entity

data class ActivateEntity(
    val isSuccess: Boolean
)

data class ActivateParams(
    val mobileNumber: String,
    val nationalId: String
)