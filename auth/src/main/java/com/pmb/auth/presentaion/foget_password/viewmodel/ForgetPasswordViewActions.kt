package com.pmb.auth.presentaion.foget_password.viewmodel

import com.pmb.core.platform.BaseViewAction

sealed interface ForgetPasswordViewActions : BaseViewAction {
    data object ClearAlert : ForgetPasswordViewActions
    data class ResetPassword(
        val nationalId: String,
        val mobileNumber: String,
        val password: String
    ) : ForgetPasswordViewActions

}