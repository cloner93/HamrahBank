package com.pmb.domain.model.openAccount.accountVerifyCode

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
@Parcelize
data class CityOfBirthInfoDTO(
    val cityCode: Int,
    val cityEnglishName: String?=null,
    val cityName: String?=null,
    val hajCityCode: Int,
    val issueRegionCode: Int,
    val macnacitiesCode: Int,
    val mrkzCode: Int,
    val provinceCode: Int,
    val status: Int,
    val statusDate: Int,
    val time: String?=null,
    val userId: String?=null
): Parcelable