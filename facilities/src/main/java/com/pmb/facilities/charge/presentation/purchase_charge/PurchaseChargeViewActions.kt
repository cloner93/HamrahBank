package com.pmb.facilities.charge.presentation.purchase_charge

import androidx.activity.compose.ManagedActivityResultLauncher
import com.pmb.core.platform.BaseViewAction
import com.pmb.facilities.charge.domain.purchase_charge.entity.Operator

sealed interface PurchaseChargeViewActions : BaseViewAction {
    data class RequestContactPermission(
        val managedActivityResultLauncher: ManagedActivityResultLauncher<String, Boolean>
    ) : PurchaseChargeViewActions
    data class SetMobileNumber(val mobileNumber:String) : PurchaseChargeViewActions
    data object GetOperatorData: PurchaseChargeViewActions
    data object SetMobileOperator: PurchaseChargeViewActions
    data object ClearMobileOperator : PurchaseChargeViewActions
}