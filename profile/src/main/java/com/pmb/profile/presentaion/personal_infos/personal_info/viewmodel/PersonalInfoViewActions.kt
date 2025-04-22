package com.pmb.profile.presentaion.personal_infos.personal_info.viewmodel

import com.pmb.core.platform.BaseViewAction
import com.pmb.profile.presentaion.personal_infos.PersonalInfoSharedState

sealed interface PersonalInfoViewActions : BaseViewAction {
    data class UpdateShareState(val sharedState: PersonalInfoSharedState) : PersonalInfoViewActions
    data object ClearAlert : PersonalInfoViewActions
    data object ChangeUsername : PersonalInfoViewActions
    data object ChangePhoneNumber : PersonalInfoViewActions
    data object ChangeAddress : PersonalInfoViewActions
    data object ChangeJob : PersonalInfoViewActions
    data object ChangeEducation : PersonalInfoViewActions
}