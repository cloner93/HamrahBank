package com.pmb.model

import kotlinx.serialization.Serializable

@Serializable
data class ResponseMetaData(
    val initialVec: String? = null,
    val statusCode: Short,
    val statusMessage: String,
    val date: Int,
    val time: Int,
    val timeStamp: Long
)

