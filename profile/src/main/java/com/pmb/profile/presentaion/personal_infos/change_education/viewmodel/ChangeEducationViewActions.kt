package com.pmb.profile.presentaion.personal_infos.change_education.viewmodel

import com.pmb.core.platform.BaseViewAction
import com.pmb.profile.presentaion.personal_infos.PersonalInfoSharedState

sealed interface ChangeEducationViewActions : BaseViewAction {
    data object ClearAlert : ChangeEducationViewActions
    data class UpdateShareState(val sharedState: PersonalInfoSharedState) :
        ChangeEducationViewActions

    data class ClickEducation(val education: String) : ChangeEducationViewActions
    data object SubmitEducation : ChangeEducationViewActions
}