package com.pmb.profile.presentaion.privacyAndSecurity.changePassword.viewmodel

import com.pmb.core.platform.BaseViewEvent

sealed interface ChangePasswordViewEvents: BaseViewEvent {
    data class ShowError(val message: String) : ChangePasswordViewEvents
    object NavigateBack : ChangePasswordViewEvents
}