package com.pmb.facilities.charge.presentation.purchase_charge

import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewState
import com.pmb.facilities.charge.domain.purchase_charge.entity.Operator

data class PurchaseChargeViewState(
    val isLoading: Boolean = false,
    val alertModelState: AlertModelState?=null,
    val mobile : String ="",
    val operatorData :List<Operator>?=null,
    val operator : Operator?=null
) : BaseViewState
