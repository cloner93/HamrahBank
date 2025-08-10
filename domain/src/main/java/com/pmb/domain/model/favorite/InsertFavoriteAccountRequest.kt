package com.pmb.domain.model.favorite

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class InsertFavoriteAccountRequest(
    val ownerDescription: String,
    val number: String
)
