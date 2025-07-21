package com.pmb.auth.presentation.first_login_confirm.viewModel

import androidx.lifecycle.viewModelScope
import com.pmb.auth.utils.startCountDown
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.Result
import com.pmb.domain.model.SendOtpRequest
import com.pmb.domain.usecae.auth.FirstLoginConfirmUseCase
import com.pmb.domain.usecae.auth.FirstLoginStepRequest
import com.pmb.domain.usecae.auth.FirstLoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FirstLoginConfirmViewModel @Inject constructor(
    initialState: FirstLoginConfirmViewState,
    private val firstLoginUseCase: FirstLoginUseCase,
    private val firstLoginConfirmUseCase: FirstLoginConfirmUseCase,
) : BaseViewModel<FirstLoginConfirmViewActions, FirstLoginConfirmViewState, FirstLoginConfirmViewEvents>(
    initialState
) {
    private var timerDurationInterval: Long = 120000L
    private var otpTryingStack = 0
    override fun handle(action: FirstLoginConfirmViewActions) {
        when (action) {
            FirstLoginConfirmViewActions.ClearAlert -> setState { it.copy(loading = false) }
            FirstLoginConfirmViewActions.ClearBottomSheet -> setState { it.copy(isShowBottomSheet = false) }
            is FirstLoginConfirmViewActions.ConfirmFirstLogin -> handleConfirmFirstLogin(action)
            is FirstLoginConfirmViewActions.ResendFirstLoginInfo -> handleResendFirstLoginInfo(
                action
            )
        }
    }

    private fun handleConfirmFirstLogin(action: FirstLoginConfirmViewActions.ConfirmFirstLogin) {
        otpTryingStack++
        viewModelScope.launch {
            firstLoginConfirmUseCase.invoke(
                SendOtpRequest(
                    mobileNumber = action.mobileNumber,
                    userName = action.userName,
                    password = action.password,
                    otp = action.otpCode
                )
            ).collect { result ->
                when (result) {
                    is Result.Success -> {
                        setState {
                            it.copy(loading = false)
                        }
                        postEvent(FirstLoginConfirmViewEvents.FirstLoginConfirmSucceed)
                    }

                    is Result.Error -> {
                        if (otpTryingStack >= 4) {
                            updateState(TimerTypeId.LOCK_TIMER, 13810000L)
                            dispatch(TimerTypeId.LOCK_TIMER, TimerEvent.STARTED)
                        }
                        setState {

                            it.copy(
                                loading = false,
                                alertModelState = if (otpTryingStack < 4) AlertModelState.Dialog(
                                    title = "خطا",
                                    description = " ${result.message}",
                                    positiveButtonTitle = "تایید",
                                    onPositiveClick = {
                                        setState { state -> state.copy(alertModelState = null) }
                                    }
                                ) else null,
                                isShowBottomSheet = otpTryingStack >= 4
                            )
                        }
                    }

                    is Result.Loading -> {
                        setState { it.copy(loading = true) }
                    }
                }
            }

        }
    }

    private fun handleResendFirstLoginInfo(action: FirstLoginConfirmViewActions.ResendFirstLoginInfo) {
        viewModelScope.launch {
            firstLoginUseCase.invoke(
                FirstLoginStepRequest(
                    mobileNumber = action.mobileNumber,
                    userName = action.userName,
                    password = action.password
                )
            ).collect { result ->
                when (result) {
                    is Result.Success -> {
                        setState {
                            it.copy(
                                loading = false,
                            )
                        }
                        updateState(
                            TimerTypeId.RESEND_TIMER,
                            timerDurationInterval,
                            timerStatus = TimerStatus.IS_RUNNING
                        )
                        dispatch(TimerTypeId.RESEND_TIMER, TimerEvent.STARTED)

                    }

                    is Result.Error -> {
                        setState {
                            it.copy(
                                loading = false,
                                alertModelState = AlertModelState.Dialog(
                                    title = "خطا",
                                    description = " ${result.message}",
                                    positiveButtonTitle = "تایید",
                                    onPositiveClick = {
                                        setState { state -> state.copy(alertModelState = null) }
                                    }
                                )

                            )
                        }
                    }

                    is Result.Loading -> {
                        updateState(
                            TimerTypeId.RESEND_TIMER,
                            0,
                            timerStatus = TimerStatus.IS_LOADING,
                        )
                    }
                }
            }
        }
    }

    private val eventChannel = Channel<Pair<TimerTypeId, TimerEvent>>(Channel.UNLIMITED)

    // call this variable for starting or stopping the countdown timer
    @OptIn(DelicateCoroutinesApi::class)
    fun dispatch(timerId: TimerTypeId, event: TimerEvent) {
        if (!eventChannel.isClosedForSend) {
            viewModelScope.launch {
                eventChannel.trySend(timerId to event)
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun startTimers() {
        viewModelScope.launch {
            eventChannel.consumeAsFlow().flatMapLatest { (timerId, event) ->
                when (event) {
                    TimerEvent.STARTED -> {
                        val initialTime =
                            viewState.value.timerState?.get(timerId)?.remainingTime ?: 0L
                        eventChannel.consumeAsFlow().startCountDown(timerId, initialTime)
                    }

                    TimerEvent.COUNTING -> {
                        val remainingTime =
                            viewState.value.timerState?.get(timerId)?.remainingTime ?: 0L
                        flowOf(timerId to remainingTime)
                    }

                    TimerEvent.FINISHED -> {
                        updateState(timerId, 0, timerStatus = TimerStatus.IS_FINISHED)
                        emptyFlow()
                    }
                }
            }.onEach { (timerId, remainingTime) ->
                updateState(
                    timerId,
                    remainingTime,
                    timerStatus = if (remainingTime >= 0) TimerStatus.IS_RUNNING else TimerStatus.IS_FINISHED,
                )
            }.launchIn(viewModelScope)
        }
    }

    private fun updateState(
        timerId: TimerTypeId,
        remainingTime: Long,
        timerStatus: TimerStatus = TimerStatus.IS_LOADING
    ) {
        if (viewState.value.timerState?.get(timerId)?.remainingTime == remainingTime) return
        setState {
            it.copy(
                timerState = it.timerState?.toMutableMap()?.apply {
                    this[timerId] = TimerState(remainingTime, timerStatus)
                }
            )
        }
    }

    init {
        setState {
            it.copy(
                timerState = mapOf(
                    (TimerTypeId.RESEND_TIMER to TimerState(remainingTime = timerDurationInterval)),
                    (TimerTypeId.LOCK_TIMER to TimerState())
                ),
//                mobileNumber = mobileNumber!!,
//                username = username!!,
//                password = password!!
            )
        }
        startTimers()
        dispatch(TimerTypeId.RESEND_TIMER, TimerEvent.STARTED)
    }


    override fun onCleared() {
        eventChannel.close()
        super.onCleared()
    }
}


