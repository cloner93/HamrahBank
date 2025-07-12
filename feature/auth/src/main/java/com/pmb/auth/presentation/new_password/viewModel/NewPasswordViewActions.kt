package com.pmb.auth.presentation.new_password.viewModel

import com.pmb.core.platform.BaseViewAction

sealed interface NewPasswordViewActions : BaseViewAction {
    data object ClearAlert : NewPasswordViewActions
    data object ClearBottomSheet : NewPasswordViewActions
    data class ChangePassWord(
        val passWord: String,
        val mobileNumber: String,
        val userName: String
    ) : NewPasswordViewActions
}