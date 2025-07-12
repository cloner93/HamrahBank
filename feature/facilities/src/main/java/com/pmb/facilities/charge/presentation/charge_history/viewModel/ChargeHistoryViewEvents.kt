package com.pmb.facilities.charge.presentation.charge_history.viewModel

import com.pmb.core.platform.BaseViewEvent

sealed interface ChargeHistoryViewEvents : BaseViewEvent {
    data class SelectedFilterIsSuccess(val filter: String) : ChargeHistoryViewEvents
}