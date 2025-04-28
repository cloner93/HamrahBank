package com.pmb.facilities.charge.domain.purchase_charge.entity

data class PurchaseChargeEntity(
    val isSuccess: Boolean,
    val data:List<Operator>
)
data class Operator(
    val id: Int,
    val operator: String,
    val operatorImage: Int,
    val isChecked: Boolean = false
)