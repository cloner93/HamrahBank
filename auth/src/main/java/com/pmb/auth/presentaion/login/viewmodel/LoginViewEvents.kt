package com.pmb.auth.presentaion.login.viewmodel

import com.pmb.core.platform.BaseViewEvent

sealed interface LoginViewEvents : BaseViewEvent{
    data object LoginSuccess : LoginViewEvents
}