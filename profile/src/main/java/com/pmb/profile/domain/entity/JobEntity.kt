package com.pmb.profile.domain.entity

import com.pmb.core.platform.DomainModel

data class JobEntity(val id: Long, val title: String) : DomainModel {

    companion object {
        fun createEmpty() = JobEntity(-1, "")
    }

    fun isNotEmpty(): Boolean = title.isNotEmpty()
}
