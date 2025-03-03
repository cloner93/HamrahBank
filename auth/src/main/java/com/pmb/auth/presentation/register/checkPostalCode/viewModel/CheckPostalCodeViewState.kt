package com.pmb.auth.presentation.register.checkPostalCode.viewModel

import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewState

data class CheckPostalCodeViewState(
    val isLoading: Boolean = false,
    val alertModelState: AlertModelState? = null,
) : BaseViewState