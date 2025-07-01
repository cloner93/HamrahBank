package com.pmb.model

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class RequestMetaData(
    val initialVec: String?,
    val imei: String,
    val osType: Int,
    val osVersion: Int,
    val deviceName: String
)