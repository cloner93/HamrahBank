package com.pmb.domain.model.openAccount.cityName

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class FetchCityListResponse(
    val cityList: List<City>
)
