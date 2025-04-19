package com.pmb.transfer.domain.entity

import com.pmb.core.platform.DomainModel

data class TransferMethodEntity(
    val title: String,
    val detail: String,
    val fee: Double,
    val active: Boolean,
    val default: Boolean = false,
    val paymentType: PaymentType
) : DomainModel
