package com.pmb.domain.model.openAccount.jobLevel

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
@Parcelize
data class JobLevel(
    val jobCode: Int, val jobName: String?=null
):Parcelable