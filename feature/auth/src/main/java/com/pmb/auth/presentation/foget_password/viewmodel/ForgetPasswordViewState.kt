package com.pmb.auth.presentation.foget_password.viewmodel

import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewState


data class ForgetPasswordViewState(
    val loading: Boolean = false,
    val alertModelState: AlertModelState? = null,
    var showBottomSheet: Boolean = false,
    var mobile: String? = null,
    var nationalId: String? = null,
    val nationalIdSerial :String?=null,
    var password: String? = null,
    var rePassword: String? = null,
    var isRenewPasswordValid: Boolean = false,
    var isPasswordValid: Boolean = false,
) : BaseViewState{
    val isEnableButton: Boolean
        get() = isRenewPasswordValid && isPasswordValid && password == rePassword && !rePassword.isNullOrEmpty() && !loading
}
