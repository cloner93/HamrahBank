package com.pmb.auth.presentation.ekyc.authentication_confirm.viewModel

import com.pmb.auth.domain.ekyc.authentication_confirm_step.entity.AuthenticationStepConfirmEntity
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewState

data class AuthenticationConfirmStepViewState(
    val loading: Boolean = false,
    val alertModelState: AlertModelState? = null,
    val data :AuthenticationStepConfirmEntity ?=null
):BaseViewState
