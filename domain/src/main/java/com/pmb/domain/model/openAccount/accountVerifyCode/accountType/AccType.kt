package com.pmb.domain.model.openAccount.accountVerifyCode.accountType

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class AccType(
    val accountType:Int,
    val accountTypeDesc:String,
    val categoryCode:Int
)
