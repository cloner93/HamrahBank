package com.pmb.facilities.charge.presentation.purchase_charge

import androidx.activity.compose.ManagedActivityResultLauncher
import com.pmb.core.platform.BaseViewAction

sealed interface PurchaseChargeViewActions : BaseViewAction {
    data class RequestContactPermission(
        val managedActivityResultLauncher: ManagedActivityResultLauncher<String, Boolean>
    ) : PurchaseChargeViewActions
    data class SetMobileNumber(val mobileNumber:String) : PurchaseChargeViewActions
    data object GetOperatorData: PurchaseChargeViewActions
    data object SetMobileOperator: PurchaseChargeViewActions
    data object ClearMobileOperator : PurchaseChargeViewActions
}