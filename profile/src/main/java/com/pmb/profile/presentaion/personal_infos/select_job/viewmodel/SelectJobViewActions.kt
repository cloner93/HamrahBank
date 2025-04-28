package com.pmb.profile.presentaion.personal_infos.select_job.viewmodel

import com.pmb.core.platform.BaseViewAction
import com.pmb.profile.domain.entity.JobEntity
import com.pmb.profile.presentaion.personal_infos.PersonalInfoSharedState

sealed interface SelectJobViewActions : BaseViewAction {
    data object ClearAlert : SelectJobViewActions
    data class UpdateSearchQuery(val query: String) : SelectJobViewActions
    data class UpdateShareState(val sharedState: PersonalInfoSharedState) : SelectJobViewActions
    data class SelectJob(val jobEntity: JobEntity) : SelectJobViewActions
}