package com.pmb.model

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class MobileApiRequest<T>(
    val metaData: RequestMetaData,
    val data: T
)