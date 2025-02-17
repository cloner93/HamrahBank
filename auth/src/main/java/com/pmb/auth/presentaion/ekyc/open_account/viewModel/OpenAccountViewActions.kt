package com.pmb.auth.presentaion.ekyc.open_account.viewModel

import com.pmb.core.platform.BaseViewAction

sealed interface OpenAccountViewActions : BaseViewAction {
    data object ClearAlert : OpenAccountViewActions
    data object OpenAccountConfirm : OpenAccountViewActions
    data object SelectRules : OpenAccountViewActions
}