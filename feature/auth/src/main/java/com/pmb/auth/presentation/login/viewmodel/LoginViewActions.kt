package com.pmb.auth.presentation.login.viewmodel

import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewAction

sealed interface LoginViewActions : BaseViewAction {
    data class Login(
        val mobileNo: String = "",
        val username: String = "",
        val password: String = "",
        val useFinger: Boolean = false
    ) : LoginViewActions

    class ShowError(val alert: AlertModelState.SnackBar) : LoginViewActions

    data object LoginWithFinger : LoginViewActions
    data object GetDataStore : LoginViewActions
    data object ClearAlert : LoginViewActions
}