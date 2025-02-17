package com.pmb.auth.presentaion.ekyc.authentication_select_services.viewModel

import com.pmb.auth.domain.ekyc.authentication_select_services.entity.SelectServicesEntity
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewState

data class AuthenticationSelectServicesViewState(
    val loading: Boolean = false,
    val alertModelState: AlertModelState? = null,
    val data: SelectServicesEntity? = null
) : BaseViewState