package com.pmb.profile.presentaion.personal_infos.change_education.viewmodel

import com.pmb.core.platform.BaseViewEvent
import com.pmb.profile.domain.entity.EducationEntity

sealed interface ChangeEducationViewEvents : BaseViewEvent {
    data class NavigateBackToPersonalInfo(val educationEntity: EducationEntity) :
        ChangeEducationViewEvents
}