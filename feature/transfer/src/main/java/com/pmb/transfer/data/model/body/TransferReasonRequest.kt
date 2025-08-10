package com.pmb.transfer.data.model.body

import kotlinx.serialization.Serializable

@Serializable
data class TransferReasonRequest(
    val transferType: Int,
    val destination: String
)
