package com.pmb.profile.domain.entity

import com.pmb.core.platform.DomainModel

data class EducationEntity(val id: Long, val title: String) : DomainModel {

    companion object {
        fun createEmpty() = EducationEntity(-1, "")
    }

    fun isNotEmpty(): Boolean = title.isNotEmpty()
}
