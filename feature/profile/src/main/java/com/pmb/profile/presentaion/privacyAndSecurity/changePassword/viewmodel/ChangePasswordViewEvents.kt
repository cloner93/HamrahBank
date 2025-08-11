package com.pmb.profile.presentaion.privacyAndSecurity.changePassword.viewmodel

import com.pmb.core.platform.AlertType
import com.pmb.core.platform.BaseViewEvent

sealed interface ChangePasswordViewEvents : BaseViewEvent {
    object NavigateBack : ChangePasswordViewEvents
    data class ShowSnackBar(val message: String, val alertType: AlertType = AlertType.Success) :
        ChangePasswordViewEvents

}