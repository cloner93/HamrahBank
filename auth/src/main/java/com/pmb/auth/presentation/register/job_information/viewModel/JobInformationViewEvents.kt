package com.pmb.auth.presentation.register.job_information.viewModel

import com.pmb.core.platform.BaseViewEvent

sealed interface JobInformationViewEvents : BaseViewEvent {
    data object SendJobInformationSucceed : JobInformationViewEvents
}