package com.pmb.profile.presentaion.personal_infos.change_phone_number.viewmodel

import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewState
import com.pmb.core.utils.MobileValidationResult
import com.pmb.core.utils.isMobile

data class ChangePhoneNumberViewState(
    val loading: Boolean = false,
    val alertState: AlertModelState? = null,
    val phoneNumber: String = "",
) : BaseViewState {
    val enableButton: Boolean
        get() = mobileValidation.isValid
    val mobileValidation: MobileValidationResult
        get() = phoneNumber.isMobile()
}
