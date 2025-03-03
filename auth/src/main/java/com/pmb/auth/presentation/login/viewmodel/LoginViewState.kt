package com.pmb.auth.presentation.login.viewmodel

import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewState

data class LoginViewState (
    val loading:Boolean=false,
    val alert:AlertModelState?=null
): BaseViewState {

}

