package com.pmb.auth.presentaion.first_login_confirm.viewModel

sealed interface FirstLoginConfirmViewActions {
    data object ClearAlert : FirstLoginConfirmViewActions
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