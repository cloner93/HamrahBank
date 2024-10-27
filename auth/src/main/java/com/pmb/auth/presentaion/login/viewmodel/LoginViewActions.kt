package com.pmb.auth.presentaion.login.viewmodel

import com.pmb.core.platform.BaseViewAction

sealed interface LoginViewActions : BaseViewAction {
    data class Login(val username:String,val password:String): LoginViewActions
    data object ClearAlert:LoginViewActions
}