package com.pmb.domain.model.card

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class FetchCityListRequest(
    val flag: Int,
    val provinceCode: Int,
)
