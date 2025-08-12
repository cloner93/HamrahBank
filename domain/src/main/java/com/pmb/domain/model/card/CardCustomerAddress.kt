package com.pmb.domain.model.card

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable


@Serializable
@SuppressLint("UnsafeOptInUsageError")
data class CardCustomerAddressRequest(
    val accountNumber: Int,
    val cardGroup: Int,
)


