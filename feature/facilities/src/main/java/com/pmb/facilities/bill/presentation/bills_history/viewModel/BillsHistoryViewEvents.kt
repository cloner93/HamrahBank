package com.pmb.facilities.bill.presentation.bills_history.viewModel

import com.pmb.core.platform.BaseViewEvent

sealed interface BillsHistoryViewEvents : BaseViewEvent {
    data class SelectedFilterIsSuccess(val filter: String) : BillsHistoryViewEvents
}