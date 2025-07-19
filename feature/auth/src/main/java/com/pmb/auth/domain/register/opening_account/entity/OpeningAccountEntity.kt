package com.pmb.auth.domain.register.opening_account.entity

data class OpeningAccountEntity(
    val isSuccess: Boolean
)
data class OpeningAccountParams(
    val phoneNumber: String,
    val nationalId: String,
    val birthDay: String
)
