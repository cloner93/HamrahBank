package com.pmb.auth.presentation.register.select_job_information.viewModel

import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewState
import com.pmb.domain.model.openAccount.jobLevel.JobLevel

data class SelectJobInformationViewState(
    val isLoading: Boolean = false,
    val alertModelState: AlertModelState? = null,
    val selectJobInformation: List<JobLevel>? = null,
    val originalSelectJobInformation: List<JobLevel>? = null,
    val searchValue : Boolean = false
) : BaseViewState