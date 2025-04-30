package com.pmb.facilities.bill.presentation.bill_identify.viewModel

import com.pmb.core.platform.BaseViewAction

sealed interface BillIdentifyViewActions : BaseViewAction {
    data object ClearAlertModelState : BillIdentifyViewActions
    data class GetBillData(val billId: String) : BillIdentifyViewActions
    data object SetBottomSheetVisibility : BillIdentifyViewActions
    data class GetTeleComBillDataBasedId(val billId: String) : BillIdentifyViewActions
    data object SetTeleComBottomSheetVisibility : BillIdentifyViewActions
}