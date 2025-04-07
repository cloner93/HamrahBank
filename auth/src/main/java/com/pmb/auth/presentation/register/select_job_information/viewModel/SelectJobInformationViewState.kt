package com.pmb.auth.presentation.register.select_job_information.viewModel

import com.pmb.auth.domain.register.select_job_information.entity.SelectJobInformationEntity
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewState

data class SelectJobInformationViewState(
    val isLoading: Boolean = false,
    val alertModelState: AlertModelState? = null,
    val selectJobInformation: SelectJobInformationEntity? = null,
    val originalSelectJobInformation: SelectJobInformationEntity? = null
) : BaseViewState