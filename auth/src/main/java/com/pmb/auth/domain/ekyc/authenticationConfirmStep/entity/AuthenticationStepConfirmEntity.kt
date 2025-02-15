package com.pmb.auth.domain.ekyc.authenticationConfirmStep.entity

data class AuthenticationStepConfirmEntity(
    val isSuccess: Boolean,
    val authenticationStepConfirmList: List<AuthenticationStepConfirmObject>
)

data class AuthenticationStepConfirmObject(
    val title: String,
    val isEnabled: Boolean
)
