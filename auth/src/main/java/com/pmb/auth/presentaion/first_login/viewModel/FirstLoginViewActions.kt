package com.pmb.auth.presentaion.first_login.viewModel

import com.pmb.core.platform.BaseViewAction

sealed interface FirstLoginViewActions : BaseViewAction {
    data object ClearAlert : FirstLoginViewActions
    data class FirstLoginStepConfirm(
        val mobileNumber: String,
        val userName: String,
        val password: String
    ) : FirstLoginViewActions
}