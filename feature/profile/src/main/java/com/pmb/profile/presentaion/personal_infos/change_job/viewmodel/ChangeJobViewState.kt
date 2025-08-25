package com.pmb.profile.presentaion.personal_infos.change_job.viewmodel

import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewState
import com.pmb.profile.domain.entity.JobEntity

data class ChangeJobViewState(
    val loading: Boolean = false,
    val alertState: AlertModelState? = null,
    val jobEntity: JobEntity = JobEntity.createEmpty(),
    val selectedJob: Boolean = false,
) : BaseViewState {
    val enableButton: Boolean
        get() = !loading && selectedJob
}
