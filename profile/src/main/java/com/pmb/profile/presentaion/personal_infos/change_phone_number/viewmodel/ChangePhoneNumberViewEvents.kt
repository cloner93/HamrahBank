package com.pmb.profile.presentaion.personal_infos.change_phone_number.viewmodel

import com.pmb.core.platform.BaseViewEvent
import com.pmb.profile.domain.entity.OtpEntity

sealed interface ChangePhoneNumberViewEvents : BaseViewEvent {
    data class NavigateToVerifyPhoneNumber(val otpEntity: OtpEntity) :
        ChangePhoneNumberViewEvents
}