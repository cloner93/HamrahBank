package com.pmb.domain.model.favorite

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class FetchFavoriteAccountRequest(
    val favoriteType: Int, //1 transfer
    val fetchFavoriteMode:Boolean
)
