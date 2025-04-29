package com.pmb.facilities.charge.presentation.charge

import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewState
import com.pmb.facilities.charge.domain.charge.entity.ChargeData
import com.pmb.facilities.charge.domain.purchase_charge.entity.Operator

data class ChargeViewState(
    val isLoading: Boolean = false,
    val alertModelState: AlertModelState?=null,
    val selectedSim: String = "",
    val simCartList: List<ChargeData>? = null,
    val operator: Operator?=null
) : BaseViewState