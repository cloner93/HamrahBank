package com.pmb.facilities.charge.domain.charge.entity

data class ChargeData(
    val id: Int,
    val imageString: Int,
    val title: String,
    val subTitle: String,
    val price: String? = null
)
data class ChargeEntity(
    val isSuccess: Boolean,
    val data :List<ChargeData>
)