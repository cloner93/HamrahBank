package com.pmb.facilities.bill.presentation.bill.viewModel

import com.pmb.core.platform.BaseViewAction
import com.pmb.facilities.bill.domain.bill.entity.BillType
import com.pmb.facilities.bill.presentation.bill_identify.viewModel.BillIdentifyViewActions

sealed interface BillViewActions : BaseViewAction {
    data object ClearAlertModelState : BillViewActions
    data object GetBillsData : BillViewActions
    data object SetBottomSheetVisibility : BillViewActions
    data class SetBillTypeData(val billType: BillType) : BillViewActions
    data class GetBillDataBasedId(val billId: String) : BillViewActions
    data object SetPurchaseBottomSheetVisibility : BillViewActions
}