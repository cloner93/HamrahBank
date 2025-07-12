package com.pmb.profile.presentaion.personal_infos.change_job.viewmodel

import com.pmb.core.platform.BaseViewAction
import com.pmb.profile.presentaion.personal_infos.PersonalInfoSharedState

sealed interface ChangeJobViewActions : BaseViewAction {
    data object ClearAlert : ChangeJobViewActions
    data class UpdateShareState(val sharedState: PersonalInfoSharedState) :
        ChangeJobViewActions

    data object ClickJob : ChangeJobViewActions
    data object SubmitJob : ChangeJobViewActions
}