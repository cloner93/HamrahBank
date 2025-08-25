package com.pmb.auth.domain.new_password.entity

data class NewPasswordEntity(
    val isSuccess: Boolean

)

data class NewPasswordParams(
    val userName: String,
    val mobileNumber: String,
    val passWord: String
)
