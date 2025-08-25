package com.pmb.auth.presentation.register.select_job_information.viewModel

import com.pmb.core.platform.BaseViewAction

sealed interface SelectJobInformationViewActions : BaseViewAction {
    data object GetSelectJobInformation : SelectJobInformationViewActions
//    data class SelectSelectJobInformation(val jobInformation: JobInformation) :
//        SelectJobInformationViewActions

    data class SearchSelectJobInformationData(val queryString: String) :
        SelectJobInformationViewActions

    data object ClearSearchData : SelectJobInformationViewActions
}