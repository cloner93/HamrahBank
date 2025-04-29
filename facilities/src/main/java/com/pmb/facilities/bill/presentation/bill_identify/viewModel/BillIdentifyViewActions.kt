package com.pmb.facilities.bill.presentation.bill_identify.viewModel

import com.pmb.core.platform.BaseViewAction

sealed interface BillIdentifyViewActions : BaseViewAction {
    data object ClearAlertModelState : BillIdentifyViewActions
}