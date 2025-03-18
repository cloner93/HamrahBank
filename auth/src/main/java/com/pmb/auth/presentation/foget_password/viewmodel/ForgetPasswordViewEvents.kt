package com.pmb.auth.presentation.foget_password.viewmodel

import com.pmb.core.platform.BaseViewEvent

sealed interface ForgetPasswordViewEvents : BaseViewEvent {
    data object ResetPasswordSuccess : ForgetPasswordViewEvents
}