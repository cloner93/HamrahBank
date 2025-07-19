package com.pmb.auth.presentation.register.authentication_information.viewModel

import com.pmb.core.platform.BaseViewEvent

sealed interface AuthenticationInformationViewEvents : BaseViewEvent {
    data object SendAuthenticationInformationViewSucceed:AuthenticationInformationViewEvents
}