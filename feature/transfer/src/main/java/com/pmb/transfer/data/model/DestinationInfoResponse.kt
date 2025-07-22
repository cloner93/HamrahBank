package com.pmb.transfer.data.model

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class DestinationInfoResponse(
    val destInfo: String?,
)
