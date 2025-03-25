package com.pmb.auth.domain.register.national_id.entity

data class RegisterNationalIdRequest(
    val nationalSerialId: String,
)

data class RegisterNationalIdEntity(
    val isSuccess: Boolean
)