package com.pmb.auth.presentation.register.register_confirm.viewModel

import com.pmb.core.platform.BaseViewAction

sealed interface RegisterConfirmStepViewActions : BaseViewAction {
    data object ClearAlert : RegisterConfirmStepViewActions
    data object LoadAuthenticationStep : RegisterConfirmStepViewActions
}