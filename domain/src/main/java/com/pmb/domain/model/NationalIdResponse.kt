package com.pmb.domain.model

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class NationalIdResponse(
    val nationalCardSeri :String?,
    val nationalCardSerial :String?
)
