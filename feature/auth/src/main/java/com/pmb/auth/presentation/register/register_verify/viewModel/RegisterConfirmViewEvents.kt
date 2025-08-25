package com.pmb.auth.presentation.register.register_verify.viewModel

import com.pmb.core.platform.BaseViewEvent

sealed interface RegisterConfirmViewEvents : BaseViewEvent {
    data object ConfirmVerifySucceed : RegisterConfirmViewEvents
}