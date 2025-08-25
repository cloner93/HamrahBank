package com.pmb.auth.presentation.register.national_id.viewModel

import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewState

data class RegisterNationalIdViewState(
    val isLoading: Boolean = false,
    val nationalSerialId :String?=null,
    val alertModelState: AlertModelState? = null,
) : BaseViewState