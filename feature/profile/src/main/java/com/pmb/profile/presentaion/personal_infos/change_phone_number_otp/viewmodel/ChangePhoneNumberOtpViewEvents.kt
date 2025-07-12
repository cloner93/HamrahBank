package com.pmb.profile.presentaion.personal_infos.change_phone_number_otp.viewmodel

import com.pmb.core.platform.BaseViewEvent
import com.pmb.profile.domain.entity.PersonalInfoEntity

sealed interface ChangePhoneNumberOtpViewEvents : BaseViewEvent {
    data class NavigateBackToPersonalInfo(val personalInfo: PersonalInfoEntity) :
        ChangePhoneNumberOtpViewEvents
}