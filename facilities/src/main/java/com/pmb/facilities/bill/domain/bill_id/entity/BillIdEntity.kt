package com.pmb.facilities.bill.domain.bill_id.entity

data class BillIdEntity(
    val billImage: Int,
    val billTitle: String,
    val billId: String,
    val billPrice: Double,
    val billPriceTitle: String,
    val billDetails: BillDetails
)

data class BillDetails(
    val billCustomer: String,
    val address: String,
    val expireDate: String
)

data class BillIdParams(
    val billId: String
)

data class TeleCommunicationEntity(
    val billImage: Int,
    val billTitle: String,
    val phoneNumber: String,
    val teleCommunicationDetails: List<TeleCommunicationDetails>
)

data class TeleCommunicationDetails(
    val id: Int,
    val title: String,
    val price: Double
)