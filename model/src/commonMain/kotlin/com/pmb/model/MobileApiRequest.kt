package com.pmb.model

import kotlinx.serialization.Serializable

@Serializable
data class MobileApiRequest<T>(
    val metaData: RequestMetaData,
    val data: T
)

