package com.pmb.facilities.bill.presentation.bill.viewModel

import com.pmb.core.platform.BaseViewAction

sealed interface BillViewActions : BaseViewAction {
    data object ClearAlertModelState : BillViewActions
    data object GetBillsData : BillViewActions
}