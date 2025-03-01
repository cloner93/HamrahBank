package com.pmb.auth.presentaion.first_login_confirm.viewModel

import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewState

data class FirstLoginConfirmViewState(
    val loading: Boolean = false,
    val alertModelState: AlertModelState? = null,
    val timerState: Map<TimerTypeId, TimerState>? = null,
    val isShowBottomSheet: Boolean = false,
) : BaseViewState {
    fun calculateSecond(timerTypeId: TimerTypeId) =
        (((timerState?.get(timerTypeId)?.remainingTime ?: 0)) % 60).toString().padStart(2, '0')

    fun calculateMinute(timerTypeId: TimerTypeId) =
        (((timerState?.get(timerTypeId)?.remainingTime
            ?: 0) / (60)) % 60).toString()
            .padStart(2, '0')

    fun calculateHour(timerTypeId: TimerTypeId) =
        (((timerState?.get(timerTypeId)?.remainingTime
            ?: 0) / (60 * 60)) % 24).toString()
            .padStart(2, '0')
}

sealed class TimerEvent {
    data object STARTED : TimerEvent()
    data object COUNTING : TimerEvent()
    data object FINISHED : TimerEvent()
}

data class TimerState(
    val remainingTime: Long = 0L,
    val timerStatus: TimerStatus = TimerStatus.IS_LOADING

)

enum class TimerStatus {
    IS_LOADING, IS_RUNNING, IS_FINISHED
}

enum class TimerTypeId {
    RESEND_TIMER,
    LOCK_TIMER,
    VIDEO_TAKEN_TIMER
}