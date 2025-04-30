package com.pmb.facilities.bill.domain.bill.entity

data class BillType(
    val id: Int,
    val type: BillsType,
    val title: String
)

enum class BillsType {
    TELECOMMUNICATION_BILL,
    GAS,
    ELECTRIC,
    WATER,
    OTHER
}