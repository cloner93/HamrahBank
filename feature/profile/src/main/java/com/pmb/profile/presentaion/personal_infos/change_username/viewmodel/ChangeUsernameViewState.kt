package com.pmb.profile.presentaion.personal_infos.change_username.viewmodel

import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewState
import com.pmb.core.utils.UsernameValidationResult

data class ChangeUsernameViewState(
    val loading: Boolean = false,
    val alertState: AlertModelState? = null,
    val username: String = ""
) : BaseViewState {
    val enableButton: Boolean
        get() = usernameConditions.isValid
    val usernameConditions: UsernameValidationResult
        get() = UsernameValidationResult.validate(username)
}
