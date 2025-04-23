package com.pmb.profile.domain.entity

import com.pmb.core.platform.DomainModel

data class PersonalInfoEntity(
    val username: String? = null,
    val phoneNumber: String? = null,
    val address: String? = null,
    val education: String? = null,
    val job: String? = null,
) : DomainModel {
    val safeUsername: String get() = username.orEmpty()
    val safePhoneNumber: String get() = phoneNumber.orEmpty()
    val safeAddress: String get() = address.orEmpty()
    val safeEducation: String get() = education.orEmpty()
    val safeJob: String get() = job.orEmpty()
}
