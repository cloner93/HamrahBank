package com.pmb.auth.presentaion.first_login_confirm.viewModel

import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewState

data class FirstLoginConfirmViewState(
    val loading: Boolean = false,
    val alertModelState: AlertModelState? = null,
    val timerStatus: TimerStatus = TimerStatus.STARTED
) : BaseViewState

enum class TimerStatus {
    STARTED, COUNTING, FINISHED
}