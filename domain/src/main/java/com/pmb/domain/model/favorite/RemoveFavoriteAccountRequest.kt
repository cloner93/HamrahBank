package com.pmb.domain.model.favorite

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class RemoveFavoriteAccountRequest(
    val number: String
)
