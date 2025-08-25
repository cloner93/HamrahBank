package com.pmb.domain.model.openAccount

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class GenerateCodeRequest(
    val nationalCode:String,
    val mobileNo:String,
    val birthDate:String
)
