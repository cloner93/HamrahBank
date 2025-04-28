package com.pmb.profile.presentaion.personal_infos.select_job.viewmodel

import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewState
import com.pmb.profile.domain.entity.JobEntity

data class SelectJobViewState(
    val loading: Boolean = false,
    val alertState: AlertModelState? = null,
    val searchQuery: String = "",
    val jobs: List<JobEntity> = emptyList()
): BaseViewState
