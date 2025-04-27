package com.pmb.profile.presentaion.personal_infos.change_username.viewmodel

import com.pmb.core.platform.BaseViewAction
import com.pmb.profile.presentaion.personal_infos.PersonalInfoSharedState

sealed interface ChangeUsernameViewActions : BaseViewAction {
    data class UpdateShareState(val sharedState: PersonalInfoSharedState) : ChangeUsernameViewActions
    data object ClearAlert : ChangeUsernameViewActions
    data class UsernameChanged(val username: String) : ChangeUsernameViewActions
    data object SubmitUsername : ChangeUsernameViewActions
}