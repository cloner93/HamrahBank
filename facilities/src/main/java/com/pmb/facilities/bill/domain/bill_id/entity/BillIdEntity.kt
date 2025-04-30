package com.pmb.facilities.bill.domain.bill_id.entity

data class BillIdEntity(
    val billImage :Int,
    val billTitle :String,
    val billId:String,
    val billPrice:Double,
    val billPriceTitle: String,
    val billDetails :BillDetails
)
data class BillDetails(
    val billCustomer: String,
    val address:String,
    val expireDate : String
)
data class BillIdParams(
    val billId: String
)