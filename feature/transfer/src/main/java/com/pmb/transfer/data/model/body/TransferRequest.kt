package com.pmb.transfer.data.model.body

import kotlinx.serialization.Serializable

@Serializable
data class TransferRequest(
    val transferType: Int,
    val source: String,
    val destination: String,
    val amount: Long,
    val payerId: Long,
    val customerId: Long,
    val commissionFee: Long,
    val reasonRowNo: Long,
    val desc: String,
    val descInfo: String,
    val saveFavorite: Boolean
)