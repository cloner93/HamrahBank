package com.pmb.auth.presentation.activation.activate.viewModel

import com.pmb.core.platform.BaseViewAction

sealed interface ActivationViewActions : BaseViewAction {
    data object ClearAlert : ActivationViewActions
    data class ActiveUser(
        val nationalId: String,
        val mobileNumber: String
    ) : ActivationViewActions

    data object SelectRules : ActivationViewActions
}