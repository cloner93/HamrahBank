package com.pmb.auth.presentation.first_login.viewModel

import com.pmb.core.platform.BaseViewAction

sealed interface FirstLoginViewActions : BaseViewAction {
    data object ClearAlert : FirstLoginViewActions
    data class UpdatePhoneNumber(val value: String) : FirstLoginViewActions
    data class UpdateUsername(val value: String) : FirstLoginViewActions
    data class UpdatePassword(val value: String) : FirstLoginViewActions
    data object FirstLoginStepConfirm : FirstLoginViewActions
}