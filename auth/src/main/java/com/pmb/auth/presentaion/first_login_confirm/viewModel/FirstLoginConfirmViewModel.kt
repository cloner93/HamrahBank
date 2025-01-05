package com.pmb.auth.presentaion.first_login_confirm.viewModel

import androidx.lifecycle.viewModelScope
import com.pmb.auth.domain.first_login.entity.FirstLoginStepRequest
import com.pmb.auth.domain.first_login.useCase.FirstLoginUseCase
import com.pmb.auth.domain.first_login_confirm.entity.SendOtpRequest
import com.pmb.auth.domain.first_login_confirm.useCase.FirstLoginConfirmUseCase
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.takeWhile
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FirstLoginConfirmViewModel @Inject constructor(
    initialState: FirstLoginConfirmViewState,
    private val firstLoginUseCase: FirstLoginUseCase,
    private val firstLoginConfirmUseCase: FirstLoginConfirmUseCase
) : BaseViewModel<FirstLoginConfirmViewActions, FirstLoginConfirmViewState, FirstLoginConfirmViewEvents>(
    initialState
) {
    val timerDurationInterval = 10L
    override fun handle(action: FirstLoginConfirmViewActions) {
        when (action) {
            FirstLoginConfirmViewActions.ClearAlert -> setState { it.copy(loading = false) }
            is FirstLoginConfirmViewActions.ConfirmFirstLogin -> handleConfirmFirstLogin(action)
            is FirstLoginConfirmViewActions.ResendFirstLoginInfo -> handleResendFirstLoginInfo(
                action
            )
        }
    }

    private fun handleConfirmFirstLogin(action: FirstLoginConfirmViewActions.ConfirmFirstLogin) {
        viewModelScope.launch {
            firstLoginConfirmUseCase.invoke(
                SendOtpRequest(
                    mobileNumber = action.mobileNumber,
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
                        setState {
                            it.copy(
                                loading = false,
                                alertModelState = AlertModelState.SnackBar(
                                    message = result.message,
                                    onActionPerformed = {
                                        setState { state -> state.copy(alertModelState = null) }
                                    },
                                    onDismissed = {
                                        setState { state -> state.copy(alertModelState = null) }
                                    }
                                )

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
                            dispatch(TimerEvent.COUNTING)
                            it.copy(
                                loading = false,
                            )
                        }

                    }

                    is Result.Error -> {
                        setState {
                            it.copy(
                                loading = false,
                                timerState = TimerState.IDLE,
                                alertModelState = AlertModelState.SnackBar(
                                    message = result.message,
                                    onActionPerformed = {
                                        setState { state -> state.copy(alertModelState = null) }
                                    },
                                    onDismissed = {
                                        setState { state -> state.copy(alertModelState = null) }
                                    }
                                )

                            )
                        }
                    }

                    is Result.Loading -> {
                        remain = 11L
                        setState { it.copy(loading = true, timerState = TimerState.LOADING) }
                    }
                }
            }
        }
    }

    private val eventChannel = Channel<TimerEvent>(Channel.RENDEZVOUS)

    @OptIn(DelicateCoroutinesApi::class)
    val dispatch = { event: TimerEvent ->
        if (!eventChannel.isClosedForSend) {
            eventChannel.trySend(event)
        }
    }
    private var remain = timerDurationInterval

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun tick() {
        eventChannel.consumeAsFlow()
            .onEach { event ->
                when (event) {
                    TimerEvent.STARTED -> timerDurationInterval
                    TimerEvent.COUNTING -> viewState.value.timerSecond
                    TimerEvent.FINISHED -> timerDurationInterval
                }

            }.flatMapLatest { event ->
                when (event) {
                    TimerEvent.COUNTING -> {
                        generateSequence(remain - 1) { it - 1 }
                            .asFlow()
                            .onEach {
                                delay(1000)
                                remain -= 1
                            }.onStart { emit(remain) }
                            .takeWhile { it >= 0 }
                            .map { second ->
                                setState {
                                    it.copy(
                                        timerState = TimerState.COUNTING,
                                        timerSecond = second
                                    )
                                }
                                second
                            }.onCompletion { second ->
                                setState {
                                    it.copy(
                                        timerState = TimerState.IDLE,
                                        timerSecond = timerDurationInterval
                                    )
                                }
                                timerDurationInterval
                            }
                    }

                    TimerEvent.FINISHED -> {
                        setState {
                            it.copy(
                                timerState = TimerState.IDLE,
                                timerSecond = timerDurationInterval
                            )
                        }
                        flowOf(timerDurationInterval)
                    }

                    TimerEvent.STARTED -> {
                        setState {
                            it.copy(
                                timerState = TimerState.COUNTING,
                                timerSecond = timerDurationInterval
                            )
                        }
                        remain = timerDurationInterval
                        flowOf(timerDurationInterval)
                    }
                }
            }
            .onEach { second ->
                setState {
                    it.copy(
                        timerSecond = second
                    )
                }
            }
            .launchIn(viewModelScope)
    }

    init {
        tick()
        dispatch(TimerEvent.COUNTING)
    }

    override fun onCleared() {
        eventChannel.close()
        super.onCleared()
    }
}
