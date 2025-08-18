package com.pmb.domain.model.card

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class City(
    val cityCode: Int,
    val cityName: String
)
