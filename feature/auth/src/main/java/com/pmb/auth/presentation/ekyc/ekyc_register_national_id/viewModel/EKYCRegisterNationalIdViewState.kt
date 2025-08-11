package com.pmb.auth.presentation.ekyc.ekyc_register_national_id.viewModel

import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewState

data class EKYCRegisterNationalIdViewState(
    val isLoading: Boolean = false,
    val alertModelState: AlertModelState? = null,
    val nationalSerialId :String?=null,
) : BaseViewState
