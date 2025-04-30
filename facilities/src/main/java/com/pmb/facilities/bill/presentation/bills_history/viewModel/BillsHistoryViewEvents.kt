package com.pmb.facilities.bill.presentation.bills_history.viewModel

import com.pmb.core.platform.BaseViewEvent
import com.pmb.facilities.charge.presentation.charge_history.viewModel.ChargeHistoryViewEvents

sealed interface BillsHistoryViewEvents : BaseViewEvent {
    data class SelectedFilterIsSuccess(val filter: String) : BillsHistoryViewEvents
}