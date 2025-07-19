package com.pmb.facilities.charge.presentation

import com.pmb.core.platform.BaseSharedState
import com.pmb.facilities.charge.domain.choose_charge_price.entity.ChoosePrice
import com.pmb.facilities.charge.domain.purchase_charge.entity.Operator

data class ChargeSharedState(
    val simNumber:String = "",
    val operator : Operator?=null,
    val chargePrice: ChoosePrice?=null
) : BaseSharedState