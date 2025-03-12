package com.pmb.auth.presentation.activation.activate.viewModel

import com.pmb.core.platform.BaseViewEvent

sealed interface ActivationViewEvents : BaseViewEvent {
    data object ActiveUserSucceed : ActivationViewEvents
}