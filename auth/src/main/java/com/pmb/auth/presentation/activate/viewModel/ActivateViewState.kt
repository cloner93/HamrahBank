package com.pmb.auth.presentation.activate.viewModel

import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewState

data class ActivateViewState(
    val loading: Boolean = false,
    val alertModelState: AlertModelState? = null,
) : BaseViewState