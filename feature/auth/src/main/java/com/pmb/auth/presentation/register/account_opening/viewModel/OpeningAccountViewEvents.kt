package com.pmb.auth.presentation.register.account_opening.viewModel

import com.pmb.core.platform.BaseViewEvent

sealed interface OpeningAccountViewEvents : BaseViewEvent{
    data object SendOpeningAccountViewSucceed:OpeningAccountViewEvents
}