package com.pmb.auth.presentaion.ekyc.authenticationSelectServices.viewModel

import com.pmb.auth.domain.ekyc.authenticationSelectServices.entity.SelectServicesEntity
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewState

data class AuthenticationSelectServicesViewState(
    val loading: Boolean = false,
    val alertModelState: AlertModelState? = null,
    val data: SelectServicesEntity? = null
) : BaseViewState