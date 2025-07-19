package com.pmb.auth.presentation.register.register_verify.viewModel

import com.pmb.core.platform.BaseViewAction

sealed interface RegisterConfirmViewActions : BaseViewAction{
    data class SetVerifyCode(val verifyCode: String): RegisterConfirmViewActions
    data object ClearBottomSheet : RegisterConfirmViewActions
    data class ConfirmVerify(
        val mobileNumber: String,
        val nationalCode: String,
        val serialId: String,
        val otpCode: Int
    ) : RegisterConfirmViewActions

    data class ResendVerifyInfo(
        val mobileNumber: String,
        val userName: String,
        val password: String
    ) : RegisterConfirmViewActions

}