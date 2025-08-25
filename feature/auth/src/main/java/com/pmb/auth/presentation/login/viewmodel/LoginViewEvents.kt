package com.pmb.auth.presentation.login.viewmodel

import com.pmb.core.platform.BaseViewEvent

sealed interface LoginViewEvents : BaseViewEvent {
    class ShowBiometricPrompt(val biometricState: Boolean) : LoginViewEvents
    data object LoginSuccess : LoginViewEvents
}