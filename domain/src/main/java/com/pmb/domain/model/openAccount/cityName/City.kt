package com.pmb.domain.model.openAccount.cityName

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
@Parcelize
data class City(
    val cityCode: Int,
    val cityName: String,
    val provinceCode: Int
): Parcelable
