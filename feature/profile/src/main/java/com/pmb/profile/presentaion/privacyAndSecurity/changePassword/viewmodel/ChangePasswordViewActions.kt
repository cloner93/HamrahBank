package com.pmb.profile.presentaion.privacyAndSecurity.changePassword.viewmodel

import com.pmb.core.platform.BaseViewAction

sealed interface ChangePasswordViewActions: BaseViewAction {
    class SetOldPassword(val oldPassword: String) : ChangePasswordViewActions
    class SetNewPassword(val newPassword: String) : ChangePasswordViewActions
    class SetNewPasswordValid(val isNewPasswordValid: Boolean) : ChangePasswordViewActions
    class SetRenewPassword(val renewPassword: String) : ChangePasswordViewActions
    object SubmitNewPassword : ChangePasswordViewActions
}