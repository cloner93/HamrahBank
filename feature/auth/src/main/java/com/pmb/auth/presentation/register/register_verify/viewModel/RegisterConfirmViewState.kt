package com.pmb.auth.presentation.register.register_verify.viewModel

import com.pmb.auth.presentation.first_login_confirm.viewModel.TimerState
import com.pmb.auth.presentation.first_login_confirm.viewModel.TimerTypeId
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewState
import com.pmb.domain.model.openAccount.accountVerifyCode.VerifyCodeResponse

data class RegisterConfirmViewState(
    val isLoading: Boolean = false,
    val alertModelState: AlertModelState? = null,
    val verifyCode: String? = null,
    val verifyCodeResponse: VerifyCodeResponse? = null,
    val isShowBottomSheet: Boolean = false,
    val timerState: Map<TimerTypeId, TimerState>? = null,
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