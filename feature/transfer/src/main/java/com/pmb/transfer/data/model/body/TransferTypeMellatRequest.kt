package com.pmb.transfer.data.model.body

import kotlinx.serialization.Serializable

@Serializable
data class TransferTypeMellatRequest(
    val source: String,
    val destination: String,
    val amount: Long,
    val payerId: Long,
    val customerId: Long,
    val saveFavorite: Boolean
)
