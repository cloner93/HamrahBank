package com.pmb.transfer.domain.entity

import com.pmb.core.platform.DomainModel

data class TransferMethodEntity(
    val title: String,
    val detail: String,
    val fee: Long,
    val active: Boolean,
    val default: Boolean = false,
    val paymentType: PaymentType
) : DomainModel
