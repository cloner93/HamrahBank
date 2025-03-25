package com.pmb.auth.presentation.first_login.viewModel

import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewState

data class FirstLoginViewState(
    val loading: Boolean = false,
    val alertModelState: AlertModelState? = null
) : BaseViewState