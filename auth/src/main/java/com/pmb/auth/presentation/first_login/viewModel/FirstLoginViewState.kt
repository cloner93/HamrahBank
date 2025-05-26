package com.pmb.auth.presentation.first_login.viewModel

import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewState
import com.pmb.core.utils.isMobile
import com.pmb.core.utils.isPassword

data class FirstLoginViewState(
    val loading: Boolean = false,
    val alertModelState: AlertModelState? = null,
    val phoneNumber: String = "",
    val username: String = "",
    val password: String = "",
) : BaseViewState {
    val enableButton: Boolean
        get() = phoneNumber.isMobile().isValid && username.length >= 6 && password.length >= 6
}