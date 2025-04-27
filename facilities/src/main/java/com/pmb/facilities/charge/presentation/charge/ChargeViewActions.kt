package com.pmb.facilities.charge.presentation.charge

import com.pmb.core.platform.BaseViewAction

sealed interface ChargeViewActions : BaseViewAction {
    data object GetLatestChargeHistory : ChargeViewActions
    data object ClearAlertModelState : ChargeViewActions
    data class SetSelectedSimNumber(val simNumber: String) : ChargeViewActions
}