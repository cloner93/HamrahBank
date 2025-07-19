package com.pmb.domain.model.openAccount.accountVerifyCode.accountType

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class Province(
    val provinceCode:Int,
    val provinceName:String
)
