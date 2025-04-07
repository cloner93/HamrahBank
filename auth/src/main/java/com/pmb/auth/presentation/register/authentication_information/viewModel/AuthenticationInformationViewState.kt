package com.pmb.auth.presentation.register.authentication_information.viewModel

import com.pmb.auth.domain.register.authentication_information.entity.GetAuthenticationEntity
import com.pmb.auth.domain.register.authentication_information.entity.SendAuthenticationInformationParam
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewState

data class AuthenticationInformationViewState(
    val isLoading: Boolean = false,
    val alertModelState: AlertModelState? = null,
    val authenticationInformation :GetAuthenticationEntity?=null,
    val originalAuthenticationInformation :GetAuthenticationEntity?=null,
    val sendAuthenticationInformationParam: SendAuthenticationInformationParam?=null
):BaseViewState