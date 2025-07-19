package com.pmb.domain.model.openAccount.accountVerifyCode

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class VerifyCodeRequest (
    val verificationCode: Int,
    val nationalCode:String,
    val mobileNo: String,
    val idSerial: String
)