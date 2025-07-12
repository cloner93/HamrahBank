package com.pmb.profile.presentaion.personal_infos.change_username.viewmodel

import com.pmb.core.platform.BaseViewEvent
import com.pmb.profile.domain.entity.PersonalInfoEntity

sealed interface ChangeUsernameViewEvents : BaseViewEvent {
    data class NavigateBackToPersonalInfo(val personalInfoEntity: PersonalInfoEntity) :
        ChangeUsernameViewEvents
}