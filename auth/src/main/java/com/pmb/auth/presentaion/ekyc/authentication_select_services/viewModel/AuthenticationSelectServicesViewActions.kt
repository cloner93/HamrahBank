package com.pmb.auth.presentaion.ekyc.authentication_select_services.viewModel

import com.pmb.core.platform.BaseViewAction

sealed interface AuthenticationSelectServicesViewActions : BaseViewAction {
    data object ClearAlert : AuthenticationSelectServicesViewActions
    data class ConfirmAuthenticationSelectedServices(
        val ids: List<Int>
    ) : AuthenticationSelectServicesViewActions

    data object LoadSelectedServicesData : AuthenticationSelectServicesViewActions
}