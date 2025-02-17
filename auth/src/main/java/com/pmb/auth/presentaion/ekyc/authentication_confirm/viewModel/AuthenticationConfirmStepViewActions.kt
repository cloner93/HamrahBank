package com.pmb.auth.presentaion.ekyc.authentication_confirm.viewModel

import com.pmb.core.platform.BaseViewAction

sealed interface AuthenticationConfirmStepViewActions : BaseViewAction {
    data object ClearAlert : AuthenticationConfirmStepViewActions
    data object LoadAuthenticationStep : AuthenticationConfirmStepViewActions
}