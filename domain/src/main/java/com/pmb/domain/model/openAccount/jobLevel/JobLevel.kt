package com.pmb.domain.model.openAccount.jobLevel

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class JobLevel(
    val jobCode: Int, val jobName: String
)
