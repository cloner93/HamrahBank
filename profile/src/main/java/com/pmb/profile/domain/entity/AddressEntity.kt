package com.pmb.profile.domain.entity

import com.pmb.core.platform.DomainModel

data class AddressEntity(
    val id: Long? = -1,
    val address: String? = "",
    val postalCode: String? = ""
) : DomainModel {
    val safeId: Long
        get() = id ?: -1
    val safeAddress: String
        get() = address.orEmpty()
    val safePostalCode: String
        get() = postalCode.orEmpty()

    val safeAddressEntity: AddressEntity
        get() = AddressEntity(
            id = safeId,
            address = safeAddress,
            postalCode = safePostalCode
        )

    fun isNotEmpty(): Boolean = !address.isNullOrEmpty() && !postalCode.isNullOrEmpty()
}
