package com.pmb.profile.presentaion.personal_infos.personal_info.viewmodel

import com.pmb.core.platform.BaseViewEvent

sealed interface PersonalInfoViewEvents : BaseViewEvent {
    data object NavigateToChangeUsername : PersonalInfoViewEvents
    data object NavigateToChangePhoneNumber : PersonalInfoViewEvents
    data object NavigateToChangeAddress : PersonalInfoViewEvents
    data object NavigateToChangeJob : PersonalInfoViewEvents
    data object NavigateToChangeEducation : PersonalInfoViewEvents
}