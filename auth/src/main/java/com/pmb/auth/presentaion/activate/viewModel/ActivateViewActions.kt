package com.pmb.auth.presentaion.activate.viewModel

import com.pmb.core.platform.BaseViewAction

sealed interface ActivateViewActions : BaseViewAction {
    data object ClearAlert : ActivateViewActions
    data class ActiveUser(
        val userName: String,
        val nationalId: String,
        val password: String,
        val mobileNumber: String
    ) : ActivateViewActions
}