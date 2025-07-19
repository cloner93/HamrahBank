package com.pmb.auth.domain.register.authentication_information.entity

data class SendAuthenticationInformationParam(
    val cityId: Int?,
    val identifyId: Int?,
    val birthDate: String?,
    val identifyArea: String?,
    val phoneNumber: String?,
    val educationId: Int?
)

