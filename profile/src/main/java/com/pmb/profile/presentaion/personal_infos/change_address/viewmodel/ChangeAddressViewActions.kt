package com.pmb.profile.presentaion.personal_infos.change_address.viewmodel

import com.pmb.core.platform.BaseViewAction
import com.pmb.profile.presentaion.personal_infos.PersonalInfoSharedState

sealed interface ChangeAddressViewActions : BaseViewAction {
    data object ClearAlert : ChangeAddressViewActions
    data class UpdateShareState(val shareState: PersonalInfoSharedState) : ChangeAddressViewActions
    data class ChangeAddress(val postalCode: String) : ChangeAddressViewActions
    data object SubmitPostalCode : ChangeAddressViewActions
    data object SubmitAddress : ChangeAddressViewActions
}