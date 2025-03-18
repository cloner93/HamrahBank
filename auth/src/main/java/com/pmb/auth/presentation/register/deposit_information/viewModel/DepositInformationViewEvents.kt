package com.pmb.auth.presentation.register.deposit_information.viewModel

import com.pmb.core.platform.BaseViewEvent

sealed interface DepositInformationViewEvents : BaseViewEvent {
    data object SendDepositInformationSucceeded : DepositInformationViewEvents
}