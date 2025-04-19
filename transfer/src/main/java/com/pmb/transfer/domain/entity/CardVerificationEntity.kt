package com.pmb.transfer.domain.entity

import com.pmb.core.platform.DomainModel

data class CardVerificationEntity(
    val id: Long,
    val duration: Int,
): DomainModel