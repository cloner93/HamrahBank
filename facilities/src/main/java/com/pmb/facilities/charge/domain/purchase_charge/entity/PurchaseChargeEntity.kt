package com.pmb.facilities.charge.domain.purchase_charge.entity

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class PurchaseChargeEntity(
    val isSuccess: Boolean,
    val data:List<Operator>
)
data class Operator(
    val id: Int,
    val operator: String,
    val operatorImage: Int,
    val isChecked: MutableState<Boolean> = mutableStateOf(false)
)