package com.pmb.profile.domain.entity

import com.pmb.core.platform.DomainModel

data class VersionEntity(
    val id: Long = 0,
    val version: String,
    val updated: Boolean,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis(),
    val message: String? = null
) : DomainModel
