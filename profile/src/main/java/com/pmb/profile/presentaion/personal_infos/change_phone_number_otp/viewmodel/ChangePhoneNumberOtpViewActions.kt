package com.pmb.profile.presentaion.personal_infos.change_phone_number_otp.viewmodel

import com.pmb.core.platform.BaseViewAction
import com.pmb.profile.presentaion.personal_infos.PersonalInfoSharedState

sealed interface ChangePhoneNumberOtpViewActions : BaseViewAction {
    data object ClearAlert : ChangePhoneNumberOtpViewActions
    data class UpdateShareState(val sharedState: PersonalInfoSharedState) :
        ChangePhoneNumberOtpViewActions

    data class UpdateOtp(val otp: String) : ChangePhoneNumberOtpViewActions
    data object ResendOtp : ChangePhoneNumberOtpViewActions
    data object SubmitOtp : ChangePhoneNumberOtpViewActions
    data object CancelTimer : ChangePhoneNumberOtpViewActions
}