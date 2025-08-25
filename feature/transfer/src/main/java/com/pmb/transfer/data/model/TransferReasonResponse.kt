package com.pmb.transfer.data.model

import kotlinx.serialization.Serializable

@Serializable
data class TransferReasonResponse(
    val rowNumber: Int,
    val code: String,
    val description: String
)
