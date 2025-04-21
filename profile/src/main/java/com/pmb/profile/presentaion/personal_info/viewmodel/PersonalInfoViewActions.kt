package com.pmb.profile.presentaion.personal_info.viewmodel

import com.pmb.core.platform.BaseViewAction

sealed interface PersonalInfoViewActions : BaseViewAction {
    data object ClearAlert : PersonalInfoViewActions
    data object ChangeUsername : PersonalInfoViewActions
    data object ChangePhoneNumber : PersonalInfoViewActions
    data object ChangeAddress : PersonalInfoViewActions
    data object ChangeJob : PersonalInfoViewActions
    data object ChangeEducation : PersonalInfoViewActions
}