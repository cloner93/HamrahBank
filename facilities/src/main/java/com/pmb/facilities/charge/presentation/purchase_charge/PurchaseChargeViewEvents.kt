package com.pmb.facilities.charge.presentation.purchase_charge

import com.pmb.core.platform.BaseViewEvent

interface PurchaseChargeViewEvents : BaseViewEvent {
    data object OpenContactList :PurchaseChargeViewEvents
}