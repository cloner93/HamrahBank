package com.pmb.auth

import com.pmb.core.platform.BaseSharedState

data class AuthSharedViewState(
    val phoneNumber: String ="",
    val userName : String ="",
    val password : String ="",
    val actionType: AuthActionType = AuthActionType.FORGET_PASSWORD,
    val isPasswordChanged : Boolean = false,
    val changePasswordPhoneNumber: String ="",
    val changePasswordPassword: String ="",
    val changePasswordNationalId: String ="",

) : BaseSharedState

enum class AuthActionType {
    FORGET_PASSWORD,
    ACTIVATION
}