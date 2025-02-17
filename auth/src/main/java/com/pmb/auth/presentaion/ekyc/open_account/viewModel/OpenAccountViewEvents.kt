package com.pmb.auth.presentaion.ekyc.open_account.viewModel

import com.pmb.core.platform.BaseViewEvent

sealed interface OpenAccountViewEvents : BaseViewEvent {
    data object OpenAccountSucceed : OpenAccountViewEvents
}