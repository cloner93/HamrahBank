package com.pmb.profile.presentaion.personal_infos.personal_info.viewmodel

import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewState
import com.pmb.profile.domain.entity.PersonalInfoEntity

data class PersonalInfoViewState(
    val loading: Boolean = false,
    val alertState: AlertModelState? = null,
    val personalInfo: PersonalInfoEntity = PersonalInfoEntity(),
) : BaseViewState {
    val showPersonalInfo: Boolean
        get() = personalInfo.safeUsername.isNotEmpty() ||
                personalInfo.safePhoneNumber.isNotEmpty() ||
                personalInfo.safeAddress.isNotEmpty() ||
                personalInfo.safeJob.isNotEmpty() ||
                personalInfo.safeEducation.isNotEmpty()
}