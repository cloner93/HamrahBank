package com.pmb.facilities.bill.presentation.bills_history.viewModel

import com.pmb.core.platform.BaseViewAction

sealed interface BillsHistoryViewActions : BaseViewAction{
    data object ClearAlertModelState : BillsHistoryViewActions
    data class GetBillsHistory(val filter: String) : BillsHistoryViewActions
    data object GetFilterData : BillsHistoryViewActions
    data class SetSelectedFilter(val filter: String) : BillsHistoryViewActions
}