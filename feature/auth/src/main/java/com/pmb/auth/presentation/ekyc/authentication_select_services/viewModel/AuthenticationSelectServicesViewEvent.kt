package com.pmb.auth.presentation.ekyc.authentication_select_services.viewModel

import com.pmb.core.platform.BaseViewEvent

sealed interface AuthenticationSelectServicesViewEvent : BaseViewEvent {
    data object SelectServicesSucceed : AuthenticationSelectServicesViewEvent
//    data class SelectService(val id: Int) : AuthenticationSelectServicesViewEvent
}