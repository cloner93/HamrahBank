package com.pmb.domain.model.openAccount.accountType

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
@Parcelize
data class Province(
    val provinceCode: Int,
    val provinceName: String
) : Parcelable
