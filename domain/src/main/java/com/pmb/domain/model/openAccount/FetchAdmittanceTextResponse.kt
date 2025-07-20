package com.pmb.domain.model.openAccount

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class FetchAdmittanceTextResponse(
    val orderId: String?=null,
    val admittanceText: String?=null
)
