package com.pmb.profile.presentaion.privacyAndSecurity.changePassword.viewmodel

import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewState

data class ChangePasswordViewState(
    val loading: Boolean = false,
    val alertState: AlertModelState? = null,
    val oldPassword: String = "",
    val isOldPasswordValid: Boolean = false,
    val newPassword: String = "",
    val isNewPasswordValid: Boolean = false,
    val renewPassword: String = "",
    val isRenewPasswordValid: Boolean = false,
    val isAllPasswordOk: Boolean = false,
    val enableSubmit: Boolean = false
) : BaseViewState