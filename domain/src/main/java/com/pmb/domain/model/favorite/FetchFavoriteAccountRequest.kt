package com.pmb.domain.model.favorite

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class FetchFavoriteAccountRequest(
    val favoriteType: Int, // 0 for favorite, 1 for recent transactions
    val fetchFavoriteMode:Boolean
)
