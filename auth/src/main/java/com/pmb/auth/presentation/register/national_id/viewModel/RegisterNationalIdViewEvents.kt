package com.pmb.auth.presentation.register.national_id.viewModel

import com.pmb.core.platform.BaseViewEvent

sealed interface RegisterNationalIdViewEvents : BaseViewEvent {
    data object RegisterNationalSucceed : RegisterNationalIdViewEvents
}