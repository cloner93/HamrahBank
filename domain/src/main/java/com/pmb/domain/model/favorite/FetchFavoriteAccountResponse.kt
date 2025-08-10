package com.pmb.domain.model.favorite

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class FetchFavoriteAccountResponse (
    val description:String,
    val number:String,
    val type:Int,
    val id:Long
)