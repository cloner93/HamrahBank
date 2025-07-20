package com.pmb.domain.model.openAccount

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class AccountArchiveJobDocResponse(
    val ids:String
)
