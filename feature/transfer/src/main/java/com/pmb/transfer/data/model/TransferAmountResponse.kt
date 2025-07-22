package com.pmb.transfer.data.model

import kotlinx.serialization.Serializable

@Serializable
data class TransferAmountResponse(
    val transferType: Int,
    val transferTypeDesc: String,
    val commissionFee: Long,
    val info: String,
    val nextCyclePaya: String?,
    val enabled: Boolean,
)