package com.pmb.auth.presentation.ekyc.ekyc_register_national_id.viewModel

import com.pmb.auth.presentation.register.national_id.viewModel.RegisterNationalIdViewActions
import com.pmb.core.platform.BaseViewAction

sealed interface EKYCRegisterNationalIdViewActions : BaseViewAction {
    data object ClearAlert : EKYCRegisterNationalIdViewActions
    data class SetNationalIdSerial(val nationalIdSerial: String) : EKYCRegisterNationalIdViewActions
}
