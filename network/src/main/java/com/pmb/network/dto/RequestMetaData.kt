package com.pmb.network.dto

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class RequestMetaData(
    val initialVec: String,
    val imei: String,
    val osType: Short,
    val osVersion: Short,
    val deviceName: String
)