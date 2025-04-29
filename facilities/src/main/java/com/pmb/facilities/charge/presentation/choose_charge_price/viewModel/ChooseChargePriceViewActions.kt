package com.pmb.facilities.charge.presentation.choose_charge_price.viewModel

import com.pmb.core.platform.BaseViewAction
import com.pmb.facilities.charge.domain.choose_charge_price.entity.ChoosePrice

sealed interface ChooseChargePriceViewActions : BaseViewAction {
    data object ClearAlertModelState : ChooseChargePriceViewActions
    data object GetChargePrice : ChooseChargePriceViewActions
    data class SetSelectedPrice(val choosePrice: ChoosePrice) : ChooseChargePriceViewActions
}