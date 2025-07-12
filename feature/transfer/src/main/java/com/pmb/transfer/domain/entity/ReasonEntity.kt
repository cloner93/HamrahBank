package com.pmb.transfer.domain.entity

import com.pmb.core.platform.DomainModel

data class ReasonEntity(
    val id: Long,
    val title: String,
    val description: String?
) : DomainModel
