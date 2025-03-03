package com.pmb.auth.presentation.first_login.viewModel

import com.pmb.core.platform.BaseViewEvent

sealed interface FirsLoginViewEvents : BaseViewEvent {
    data object FirstLoginStepSucceed : FirsLoginViewEvents
}