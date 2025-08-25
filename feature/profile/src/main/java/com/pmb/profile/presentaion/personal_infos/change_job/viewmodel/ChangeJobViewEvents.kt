package com.pmb.profile.presentaion.personal_infos.change_job.viewmodel

import com.pmb.core.platform.BaseViewEvent
import com.pmb.profile.domain.entity.JobEntity

sealed interface ChangeJobViewEvents : BaseViewEvent {
    data class NavigateToListJob(val jobs: List<JobEntity>) : ChangeJobViewEvents
    data class NavigateBackToPersonalInfo(val jobEntity: JobEntity) : ChangeJobViewEvents
}