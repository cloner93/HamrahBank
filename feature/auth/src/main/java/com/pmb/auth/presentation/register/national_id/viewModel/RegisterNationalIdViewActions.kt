package com.pmb.auth.presentation.register.national_id.viewModel

import com.pmb.core.platform.BaseViewAction

sealed interface RegisterNationalIdViewActions : BaseViewAction {
    data object ClearAlert : RegisterNationalIdViewActions
    data class RegisterNationalIdSerialServices(
        val nationalSerialId: String
    ) : RegisterNationalIdViewActions
}