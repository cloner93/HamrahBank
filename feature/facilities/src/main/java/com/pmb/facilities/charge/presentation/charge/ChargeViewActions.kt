package com.pmb.facilities.charge.presentation.charge

import com.pmb.core.platform.BaseViewAction
import com.pmb.facilities.charge.domain.charge.entity.ChargeData

sealed interface ChargeViewActions : BaseViewAction {
    data object GetLatestChargeHistory : ChargeViewActions
    data object ClearAlertModelState : ChargeViewActions
    data class SetSelectedSimNumber(val simNumber: ChargeData) : ChargeViewActions
}