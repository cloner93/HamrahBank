package com.pmb.auth.presentaion.login.viewmodel

import com.pmb.core.platform.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    initialState:LoginViewState,
) : BaseViewModel<LoginViewActions,LoginViewState,LoginViewEvents>(initialState) {


    override fun handle(action: LoginViewActions) {

    }
}