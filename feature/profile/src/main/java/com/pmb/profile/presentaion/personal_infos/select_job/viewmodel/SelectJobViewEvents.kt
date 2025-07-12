package com.pmb.profile.presentaion.personal_infos.select_job.viewmodel

import com.pmb.core.platform.BaseViewEvent
import com.pmb.profile.domain.entity.JobEntity

interface SelectJobViewEvents : BaseViewEvent {
    data class NavigateBackToChangeJob(val jobEntity: JobEntity) : SelectJobViewEvents
}