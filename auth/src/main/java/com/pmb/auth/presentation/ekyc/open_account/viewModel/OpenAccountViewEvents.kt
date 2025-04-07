package com.pmb.auth.presentation.ekyc.open_account.viewModel

import com.pmb.core.platform.BaseViewEvent

sealed interface OpenAccountViewEvents : BaseViewEvent {
    data object OpenAccountSucceed : OpenAccountViewEvents
}