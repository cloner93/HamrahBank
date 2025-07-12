package com.pmb.auth.presentation.register.check_postal_code.viewModel

import com.pmb.core.platform.BaseViewAction

sealed interface CheckPostalCodeViewActions : BaseViewAction {
    data object ClearAlert : CheckPostalCodeViewActions
    data class CheckPostalCode(val postalCode: String) : CheckPostalCodeViewActions
    data class CheckAddress(val postalCode: String, val address: String) :
        CheckPostalCodeViewActions
}