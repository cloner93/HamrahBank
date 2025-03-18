package com.pmb.auth.presentation.register.check_postal_code.viewModel

import com.pmb.core.platform.BaseViewEvent

sealed interface CheckPostalCodeViewEvents : BaseViewEvent {
    data class CheckPostalCode(val address: String) : CheckPostalCodeViewEvents
    data object CheckAddressSucceed : CheckPostalCodeViewEvents
}