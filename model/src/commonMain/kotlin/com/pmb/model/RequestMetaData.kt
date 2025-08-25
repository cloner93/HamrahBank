package com.pmb.model

import kotlinx.serialization.Serializable

@Serializable
data class RequestMetaData(
    val initialVec: String?,
    val imei: String,
    val osType: Int,
    val osVersion: Int,
    val deviceName: String
)

