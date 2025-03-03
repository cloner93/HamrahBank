package com.pmb.auth.presentation.activate.viewModel

import com.pmb.core.platform.BaseViewEvent

sealed interface ActivateViewEvents : BaseViewEvent {
    data object ActiveUserSucceed : ActivateViewEvents
}