package com.pmb.auth.domain.first_login_confirm.entity

data class SendOtpRequest(
    val mobileNumber: String,
    val otp: String
)

data class SendOtpResponse(
    val isSuccess: Boolean
)