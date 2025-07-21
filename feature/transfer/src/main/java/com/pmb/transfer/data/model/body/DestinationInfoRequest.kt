package com.pmb.transfer.data.model.body

import kotlinx.serialization.Serializable

@Serializable
data class DestinationInfoRequest(
    val destination: String,
)
