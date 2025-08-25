package com.pmb.model

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class ResponseMetaData(
    val initialVec: String? = null,
    val statusCode: Short,
    val statusMessage: String,
    val date: Int,
    val time: Int,
    val timeStamp: Long
)