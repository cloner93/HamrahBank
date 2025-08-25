package com.pmb.profile.presentaion.personal_infos.change_username.viewmodel

import com.pmb.core.platform.BaseViewEvent

sealed interface ChangeUsernameViewEvents : BaseViewEvent {
    data class NavigateBackToPersonalInfo(val newUsername: String) :
        ChangeUsernameViewEvents
}