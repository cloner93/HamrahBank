package com.pmb.auth.presentation.register.job_information.viewModel

import com.pmb.auth.domain.register.select_job_information.entity.JobInformation
import com.pmb.core.platform.BaseViewAction

sealed interface JobInformationViewActions : BaseViewAction {
    data object ClearAlert : JobInformationViewActions
    data object GetAnnualIncomePrediction : JobInformationViewActions
    data class SetJobInformation(val jobInformation: JobInformation) : JobInformationViewActions
    data class SendJonInformation(val annualId: Int, val jobId: Int) :
        JobInformationViewActions
}