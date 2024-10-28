package com.pmb.auth.presentaion.foget_password.viewmodel

import com.pmb.core.platform.BaseViewEvent

sealed interface ForgetPasswordViewEvents : BaseViewEvent {
    data object ResetPasswordSuccess : ForgetPasswordViewEvents
}