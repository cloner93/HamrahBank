package com.pmb.auth.presentaion.first_login_confirm.viewModel

import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewState

data class FirstLoginConfirmViewState(
    val loading: Boolean = false,
    val alertModelState: AlertModelState? = null,
    val timerState: TimerState = TimerState.COUNTING,
    val timerSecond: Long = 0L,
    val isShowBottomSheet: Boolean = false,
    val attemptSendConfirmationError: Boolean = false,
    val timerType: TimerType = TimerType.RESEND_TYPE
) : BaseViewState {
    val hour: String
        get() {
            return (((timerSecond) / (60 * 60)) % 24).toString().padStart(2, '0')
        }
    val minute: String
        get() {
            return (((timerSecond) / (60)) % 60).toString().padStart(2, '0')
        }
    val second: String
        get() {
            return ((timerSecond) % 60).toString().padStart(2, '0')
        }
}

enum class TimerType {
    RESEND_TYPE, BOTTOM_SHEET_ERROR
}

enum class TimerEvent {
    STARTED, COUNTING, FINISHED
}

enum class TimerState {
    LOADING, IDLE, COUNTING
}