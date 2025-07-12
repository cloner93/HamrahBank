package com.pmb.profile.presentaion.personal_infos.change_phone_number.viewmodel

import com.pmb.core.platform.BaseViewAction
import com.pmb.profile.presentaion.personal_infos.PersonalInfoSharedState

sealed interface ChangePhoneNumberViewActions : BaseViewAction {
    data object ClearAlert : ChangePhoneNumberViewActions
    data class UpdateShareState(val sharedState: PersonalInfoSharedState) :
        ChangePhoneNumberViewActions

    data class ChangePhoneNumber(val phoneNumber: String) : ChangePhoneNumberViewActions
    data object SubmitPhoneNumber : ChangePhoneNumberViewActions
}