package com.pmb.domain.model.card

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable

@Serializable
@SuppressLint("UnsafeOptInUsageError")
data class CardFetchPostCodeRequest(
    val postalCode: Long
)
@Serializable
@SuppressLint("UnsafeOptInUsageError")
data class CardFetchPostCodeResponse(
    val address: String
)
