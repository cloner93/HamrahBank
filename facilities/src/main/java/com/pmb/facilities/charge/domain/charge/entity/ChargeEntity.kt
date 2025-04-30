package com.pmb.facilities.charge.domain.charge.entity

import com.pmb.facilities.bill.domain.bill.entity.BillsType

data class ChargeData(
    val id: Int,
    val imageString: Int,
    val title: String,
    val subTitle: String,
    val price: String? = null,
    val type: BillsType? = null
)

data class ChargeEntity(
    val isSuccess: Boolean,
    val data: List<ChargeData>
)