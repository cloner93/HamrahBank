package com.pmb.auth.presentaion.ekyc.openAccount.viewModel

import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewState

data class OpenAccountViewState(
    val loading: Boolean = false,
    val alertModelState: AlertModelState? = null,
    val isChecked :Boolean =false
):BaseViewState
