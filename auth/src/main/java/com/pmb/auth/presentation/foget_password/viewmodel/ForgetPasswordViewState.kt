package com.pmb.auth.presentation.foget_password.viewmodel

import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewState

data class ForgetPasswordViewState (
    val loading:Boolean = false,
    val alert: AlertModelState?=null
) : BaseViewState