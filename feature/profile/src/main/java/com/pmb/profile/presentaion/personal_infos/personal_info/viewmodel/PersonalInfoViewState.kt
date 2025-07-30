package com.pmb.profile.presentaion.personal_infos.personal_info.viewmodel

import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewState
import com.pmb.profile.domain.entity.PersonalInfoEntity

data class PersonalInfoViewState(
    val loading: Boolean = false,
    val alertState: AlertModelState? = null,
    val showPersonalInfo: Boolean = true,
    val personalInfo: PersonalInfoEntity = PersonalInfoEntity(),
) : BaseViewState