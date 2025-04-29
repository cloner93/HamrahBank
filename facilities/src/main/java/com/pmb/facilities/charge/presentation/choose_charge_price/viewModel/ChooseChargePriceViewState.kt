package com.pmb.facilities.charge.presentation.choose_charge_price.viewModel

import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewState
import com.pmb.facilities.charge.domain.choose_charge_price.entity.ChoosePrice

data class ChooseChargePriceViewState(
    val isLoading: Boolean = false,
    val alertModelState: AlertModelState? = null,
    val chargePriceData :List<ChoosePrice> ?=null,
    val selectedPrice :ChoosePrice ?=null
) : BaseViewState
