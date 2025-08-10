package com.pmb.domain.model.openAccount

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class FetchCardFormatResponse(
    val incomeType: Int?,
    val actionNumber: Int?,
    val formatId: Int?,
    val cardGroup: Int?,
    val image: String?,
    val imageStr: String?,
    val backImage: String?,
    val backImageStr: String?,
    val formatDescription: String?,
    val commonAmount: Int?
)
