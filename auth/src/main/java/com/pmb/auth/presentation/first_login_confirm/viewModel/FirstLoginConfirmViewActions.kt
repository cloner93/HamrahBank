package com.pmb.auth.presentation.first_login_confirm.viewModel

import com.pmb.core.platform.BaseViewAction

sealed interface FirstLoginConfirmViewActions : BaseViewAction {
    data object ClearAlert : FirstLoginConfirmViewActions
    data object ClearBottomSheet : FirstLoginConfirmViewActions
    data class ConfirmFirstLogin(
        val mobileNumber: String,
        val otpCode: String
    ) : FirstLoginConfirmViewActions

    data class ResendFirstLoginInfo(
        val mobileNumber: String,
        val userName: String,
        val password: String
    ) : FirstLoginConfirmViewActions
}