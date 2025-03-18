package com.pmb.auth.domain.register.check_postal_code.entity

data class CheckPostalCodeRequest(
    val postalCode: String
)

data class CheckPostalCodeResponse(
    val isSuccess: Boolean,
    val address: String
)

data class SendAddressRequest(
    val postalCode: String,
    val address: String
)

data class SendAddressResponse(
    val isSuccess: Boolean,
)