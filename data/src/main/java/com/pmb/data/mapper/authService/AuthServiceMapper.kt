package com.pmb.data.mapper.authService

import com.pmb.domain.model.LoginResponse
import com.pmb.domain.model.RegisterRequest
import com.pmb.domain.model.RegisterVerifyResponse
import com.pmb.domain.model.SendOtpRequest
import com.pmb.domain.model.SendOtpResponse
import com.pmb.model.ResponseMetaData

fun SendOtpRequest.toData(): RegisterRequest {
    return RegisterRequest(
        customerId = this.mobileNumber,
        userName = this.userName,
        password = this.password,
        vcode = this.otp.toInt()
    )
}

fun RegisterVerifyResponse.toDomain(): SendOtpResponse {
    return SendOtpResponse(
        isSuccess = true,
        customerId = this.customerId,
        desKey = this.desKey,
        passwordX = this.passwordX,
        securePassword = this.securePassword,
        userName = this.userName,
        email = this.email,
        imeiNo = this.imeiNO
    )
}

fun LoginResponse.mapToLoginResponse(): LoginResponse {
    return LoginResponse(
        isSuccess = true,
        customerId = this.customerId,
        desKey = this.desKey,
        passwordX = this.passwordX,
        securePassword = this.securePassword,
        userName = this.userName,
        email = this.email,
        imeiNo = this.imeiNo
    )
}

fun ResponseMetaData?.toDomain(): Boolean {
//    if (this)
    return this?.statusMessage?.takeIf { it == "موفق" }?.let { true } ?: run { false }
}