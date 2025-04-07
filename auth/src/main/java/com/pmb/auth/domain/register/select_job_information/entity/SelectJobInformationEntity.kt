package com.pmb.auth.domain.register.select_job_information.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class SelectJobInformationEntity(
    val isSuccess: Boolean,
    val selectJobInformation: List<JobInformation>
)

@Parcelize
data class JobInformation(
    val id: Int,
    val jobInformation: String,
) : Parcelable

