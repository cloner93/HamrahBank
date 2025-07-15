package com.pmb.auth.presentation.login.viewmodel

import com.pmb.core.platform.BaseViewAction

sealed interface LoginViewActions : BaseViewAction {
    data class Login(val mobileNo: String,val username:String, val password:String): LoginViewActions
    data object GetDataStore: LoginViewActions
    data object ClearAlert:LoginViewActions
}