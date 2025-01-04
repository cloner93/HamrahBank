package com.pmb.auth.presentaion.first_login_confirm.viewModel

import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewState

data class FirstLoginConfirmViewState(
    val loading: Boolean = false,
    val alertModelState: AlertModelState? = null
) : BaseViewState

data class FirstLoginTimerViewState(
    val startTimer: Boolean = false,
    val finishTimer: Boolean = false
) : BaseViewState