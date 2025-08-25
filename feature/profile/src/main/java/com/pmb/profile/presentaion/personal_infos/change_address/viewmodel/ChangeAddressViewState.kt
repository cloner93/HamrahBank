package com.pmb.profile.presentaion.personal_infos.change_address.viewmodel

import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewState

data class ChangeAddressViewState(
    val loading: Boolean = false,
    val alertState: AlertModelState? = null,
    val postalCode: String = "",
    val address: String = "",
    val newInquiry: Boolean = false,
    val enablePostalCodeButton: Boolean = false
) : BaseViewState {
    val enableSubmitButton: Boolean
        get() = postalCode.length == 10 && address.isNotEmpty() == true
}
