package com.pmb.domain.model.openAccount.jobLevel

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class FetchJobLevelResponse(val jobList: List<JobLevel>)
