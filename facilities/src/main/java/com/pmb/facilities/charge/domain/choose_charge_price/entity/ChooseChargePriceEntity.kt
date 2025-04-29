package com.pmb.facilities.charge.domain.choose_charge_price.entity

import androidx.compose.runtime.MutableState

data class ChooseChargePriceEntity(
    val isSuccess: Boolean,
    val data : List<ChoosePrice>
)

data class ChoosePrice(
    val id: Int,
    val price: String,
    val isChecked: MutableState<Boolean>
)
