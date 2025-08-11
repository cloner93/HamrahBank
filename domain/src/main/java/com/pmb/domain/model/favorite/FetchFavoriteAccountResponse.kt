package com.pmb.domain.model.favorite

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class FetchFavoriteAccountResponse (
    val description:String,
    val number:String,
    val type:Int, //0 favorite //recent 1
    val id:Long
)