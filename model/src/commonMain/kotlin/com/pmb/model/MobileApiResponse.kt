package com.pmb.model

import kotlinx.serialization.Serializable

@Serializable
data class MobileApiResponse<T>(
    val status: Int,
    val metaData: ResponseMetaData?,
    var data: T? = null
)

