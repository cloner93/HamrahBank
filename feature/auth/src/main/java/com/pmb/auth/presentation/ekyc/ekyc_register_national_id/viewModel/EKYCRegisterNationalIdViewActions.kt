package com.pmb.auth.presentation.ekyc.ekyc_register_national_id.viewModel

import com.pmb.core.platform.BaseViewAction

sealed interface EKYCRegisterNationalIdViewActions : BaseViewAction {
    data object ClearAlert : EKYCRegisterNationalIdViewActions
    data class RegisterNationalIdSerialServices(
        val nationalSerialId: String
    ) : EKYCRegisterNationalIdViewActions
}
