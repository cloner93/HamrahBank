package com.pmb.profile.presentaion.personal_infos

import com.pmb.core.platform.BaseSharedState
import com.pmb.core.platform.BaseSharedViewModel
import com.pmb.profile.domain.entity.AddressEntity
import com.pmb.profile.domain.entity.JobEntity
import com.pmb.profile.domain.entity.OtpEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PersonalInfoSharedViewModel @Inject constructor() :
    BaseSharedViewModel<PersonalInfoSharedState>(PersonalInfoSharedState())

data class PersonalInfoSharedState(
    val username: String? = null,
    val phoneNumber: String? = null,
    val addressEntity: AddressEntity? = AddressEntity(),
    val jobEntity: JobEntity? = null,
    val education: String? = null,
    val otpEntity: OtpEntity? = null,
    val jobEntities: List<JobEntity> = emptyList(),
    val queueJob: JobEntity? = null,
) : BaseSharedState