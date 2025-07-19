package com.pmb.auth.presentation.login.viewmodel

import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewState
import com.pmb.domain.model.UserData

data class LoginViewState (
    val isLoading:Boolean=false,
    val alert:AlertModelState?=null,
    val userData: UserData?=null
): BaseViewState

