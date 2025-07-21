package com.pmb.transfer.data.model.body

import kotlinx.serialization.Serializable

@Serializable
data class TransferAmountRequest(
    val amount: Long,
    val destination: String
)
