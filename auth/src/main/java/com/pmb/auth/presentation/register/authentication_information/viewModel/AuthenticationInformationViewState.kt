package com.pmb.auth.presentation.register.authentication_information.viewModel

import com.pmb.core.platform.AlertModelState

data class AuthenticationInformationViewState(
    val isLoading: Boolean? = false,
    val alertModelState: AlertModelState? = null
)