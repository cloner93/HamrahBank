package com.pmb.auth.presentation.foget_password.viewmodel

import com.pmb.core.platform.BaseViewAction

sealed interface ForgetPasswordViewActions : BaseViewAction {
    data object ClearAlert : ForgetPasswordViewActions
    data object ResetPassword : ForgetPasswordViewActions
    data class SetNationalId(val nationalId: String) : ForgetPasswordViewActions
    data class SetNewPassword(val newPassword: String) : ForgetPasswordViewActions
    data class SetNewPasswordValid(val newPasswordValid: Boolean) : ForgetPasswordViewActions
    data class SetReNewPassword(val renewPassword: String) : ForgetPasswordViewActions
    data class SetReNewPasswordValid(val renewPasswordValid: Boolean) : ForgetPasswordViewActions
    data class SetMobile(val mobile: String) : ForgetPasswordViewActions
}