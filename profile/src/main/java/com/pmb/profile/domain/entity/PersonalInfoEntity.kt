package com.pmb.profile.domain.entity

import com.pmb.core.platform.DomainModel

data class PersonalInfoEntity(
    val username: String? = null,
    val phoneNumber: String? = null,
    val addressEntity: AddressEntity? = AddressEntity(),
    val educationEntity: EducationEntity? = null,
    val jobEntity: JobEntity? = null,
) : DomainModel {
    val safeUsername: String get() = username.orEmpty()
    val safePhoneNumber: String get() = phoneNumber.orEmpty()
    val safeAddressEntity: AddressEntity get() = addressEntity ?: AddressEntity().safeAddressEntity
    val safeEducation: EducationEntity get() = educationEntity ?: EducationEntity.createEmpty()
    val safeJobEntity: JobEntity get() = jobEntity ?: JobEntity.createEmpty()
}
