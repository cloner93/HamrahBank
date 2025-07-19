package com.pmb.auth.presentation.register.register_verify.viewModel

import androidx.lifecycle.viewModelScope
import com.pmb.auth.presentation.first_login_confirm.viewModel.TimerEvent
import com.pmb.auth.presentation.first_login_confirm.viewModel.TimerState
import com.pmb.auth.presentation.first_login_confirm.viewModel.TimerStatus
import com.pmb.auth.presentation.first_login_confirm.viewModel.TimerTypeId
import com.pmb.auth.utils.startCountDown
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.Result
import com.pmb.domain.usecae.auth.FirstLoginConfirmUseCase
import com.pmb.domain.usecae.auth.FirstLoginStepRequest
import com.pmb.domain.usecae.auth.FirstLoginUseCase
import com.pmb.domain.usecae.auth.openAccount.AccountVerifyCodeUseCase
import com.pmb.domain.usecae.auth.openAccount.VerifyCodeParams
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
class RegisterConfirmViewModel @Inject constructor(
    private val firstLoginUseCase: FirstLoginUseCase,
    private val firstLoginConfirmUseCase: FirstLoginConfirmUseCase,
    private val verifyCodeUseCase: AccountVerifyCodeUseCase
) :
    BaseViewModel<RegisterConfirmViewActions, RegisterConfirmViewState, RegisterConfirmViewEvents>(
        RegisterConfirmViewState()
    ) {
    private var timerDurationInterval: Long = 120000L
    private var otpTryingStack = 0
    override fun handle(action: RegisterConfirmViewActions) {
        when (action) {
            is RegisterConfirmViewActions.SetVerifyCode -> {
                handleVerifyCode(action)
            }

            RegisterConfirmViewActions.ClearBottomSheet -> setState { it.copy(isShowBottomSheet = false) }
            is RegisterConfirmViewActions.ConfirmVerify -> handleConfirmFirstLogin(action)
            is RegisterConfirmViewActions.ResendVerifyInfo -> handleResendFirstLoginInfo(
                action
            )
        }
    }

    private fun handleConfirmFirstLogin(action: RegisterConfirmViewActions.ConfirmVerify) {
        otpTryingStack++
        viewModelScope.launch {
            verifyCodeUseCase.invoke(
                VerifyCodeParams(
                    mobileNo = action.mobileNumber,
                    nationalCode = action.nationalCode,
                    idSerial = action.serialId,
                    verificationCode = action.otpCode
                )
            ).collect { result ->
                when (result) {
                    is Result.Success -> {
                        setState {
                            it.copy(
                                isLoading = false,
                                verifyCodeResponse = result.data
                            )
                        }
                        postEvent(RegisterConfirmViewEvents.ConfirmVerifySucceed)
                    }

                    is Result.Error -> {
                        if (otpTryingStack >= 4) {
                            updateState(TimerTypeId.LOCK_TIMER, 13810000L)
                            dispatch(TimerTypeId.LOCK_TIMER, TimerEvent.STARTED)
                        }
                        setState {

                            it.copy(
                                isLoading = false,
                                alertModelState =
//                                    if (otpTryingStack < 4)
                                    AlertModelState.Dialog(
                                        title = "خطا",
                                        description = " ${result.message}",
                                        positiveButtonTitle = "تایید",
                                        onPositiveClick = {
                                            setState { state -> state.copy(alertModelState = null) }
                                        }
                                    )
//                                else null,
//                                isShowBottomSheet = otpTryingStack >= 4
                            )
                        }
                    }

                    is Result.Loading -> {
                        setState { it.copy(isLoading = true) }
                    }
                }
            }

        }
    }

    private fun handleResendFirstLoginInfo(action: RegisterConfirmViewActions.ResendVerifyInfo) {
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
                                isLoading = false,
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
                                isLoading = false,
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

    private fun handleVerifyCode(code: RegisterConfirmViewActions.SetVerifyCode) {
        setState {
            it.copy(
                verifyCode = code.verifyCode
            )
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