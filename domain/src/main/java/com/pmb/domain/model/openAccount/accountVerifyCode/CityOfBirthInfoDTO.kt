package com.pmb.domain.model.openAccount.accountVerifyCode

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class CityOfBirthInfoDTO(
    val cityCode: Int,
    val cityLatinName: String?=null,
    val cityName: String?=null,
    val hajCityCode: Int,
    val issueRegionCode: Int,
    val macnacitiesCode: Int,
    val markazCode: Int,
    val provinceCode: Int,
    val status: Int,
    val statusDate: Int,
    val time: String?=null,
    val userId: String?=null
)