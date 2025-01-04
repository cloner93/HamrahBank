package com.pmb.auth.domain.first_login.entity

data class FirstLoginStepRequest(
    val mobileNumber: String,
    val userName: String,
    val password: String
)

data class FirstLoginStepResponse(
    val isSuccess: Boolean
)