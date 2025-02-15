package com.pmb.auth.presentaion.ekyc.authenthicationConfirm.viewModel

import com.pmb.auth.domain.ekyc.authenticationConfirmStep.entity.AuthenticationStepConfirmEntity
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewState

data class AuthenticationConfirmStepViewState(
    val loading: Boolean = false,
    val alertModelState: AlertModelState? = null,
    val data :AuthenticationStepConfirmEntity ?=null
):BaseViewState
