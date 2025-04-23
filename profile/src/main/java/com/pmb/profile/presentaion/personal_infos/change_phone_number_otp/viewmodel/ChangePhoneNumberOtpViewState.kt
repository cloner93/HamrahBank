package com.pmb.profile.presentaion.personal_infos.change_phone_number_otp.viewmodel

import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewState
import com.pmb.profile.domain.entity.OtpEntity

data class ChangePhoneNumberOtpViewState(
    val loading: Boolean = false,
    val alertState: AlertModelState? = null,
    val otp: String = "",
    val phoneNumber: String = "",
    val timer: Int = 0,
    val otpEnabled: OtpEntity? = null,
    val enableResend: Boolean = false,
) : BaseViewState {
    val enableSubmit: Boolean
        get() = otp.isNotEmpty() && otp.length >= 4

    val showTimer: Boolean
        get() = !enableResend && timer > 0
}
