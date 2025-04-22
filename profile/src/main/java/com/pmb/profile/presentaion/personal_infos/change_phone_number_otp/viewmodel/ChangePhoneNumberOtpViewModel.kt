package com.pmb.profile.presentaion.personal_infos.change_phone_number_otp.viewmodel

import androidx.lifecycle.viewModelScope
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.Result
import com.pmb.profile.domain.use_case.ResendOtpCodeUseCase
import com.pmb.profile.domain.use_case.SubmitOtpCodeUseCase
import com.pmb.profile.presentaion.personal_infos.PersonalInfoSharedState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChangePhoneNumberOtpViewModel @Inject constructor(
    private val resendOtpCodeUseCase: ResendOtpCodeUseCase,
    private val submitOtpCodeUseCase: SubmitOtpCodeUseCase
) : BaseViewModel<ChangePhoneNumberOtpViewActions, ChangePhoneNumberOtpViewState, ChangePhoneNumberOtpViewEvents>(
    ChangePhoneNumberOtpViewState()
) {
    private val _shareState = MutableStateFlow<PersonalInfoSharedState>(PersonalInfoSharedState())
    val shareState: StateFlow<PersonalInfoSharedState> = _shareState


    override fun handle(action: ChangePhoneNumberOtpViewActions) {
        when (action) {
            ChangePhoneNumberOtpViewActions.ClearAlert -> setState { it.copy(alertState = null) }
            is ChangePhoneNumberOtpViewActions.UpdateShareState -> updateShareState(action.sharedState)
            is ChangePhoneNumberOtpViewActions.UpdateOtp -> setState { it.copy(otp = action.otp) }
            ChangePhoneNumberOtpViewActions.ResendOtp -> handleResendOtp()
            ChangePhoneNumberOtpViewActions.SubmitOtp -> handleSubmitOtp()
            ChangePhoneNumberOtpViewActions.CancelTimer -> cancelCountdown()
        }
    }


    private fun updateShareState(shareState: PersonalInfoSharedState) {
        _shareState.value = shareState
        shareState.otpEntity?.let { otpEntity ->
            setState { it.copy(phoneNumber = otpEntity.phoneNumber) }
            startCountdown(from = otpEntity.duration)
        }
    }

    private fun handleResendOtp() {
        val otpEntity = shareState.value.otpEntity ?: return
        viewModelScope.launch {
            resendOtpCodeUseCase.invoke(
                ResendOtpCodeUseCase.Param(
                    id = otpEntity.id, phoneNumber = otpEntity.phoneNumber
                )
            ).collect { result ->
                when (result) {
                    is Result.Error -> {
                        setState {
                            it.copy(
                                loading = false,
                                enableResend = true,
                                alertState = AlertModelState.SnackBar(
                                    message = result.message,
                                    onActionPerformed = {
                                        setState { it.copy(loading = false) }
                                    },
                                    onDismissed = {
                                        setState { it.copy(loading = false) }
                                    })
                            )
                        }
                    }

                    Result.Loading -> {
                        setState {
                            it.copy(
                                loading = true,
                                enableResend = false
                            )
                        }
                    }

                    is Result.Success -> {
                        setState {
                            it.copy(
                                loading = false,
                                timer = result.data.duration,
                            )
                        }
                        startCountdown(from = result.data.duration)
                    }
                }
            }
        }
    }

    private fun handleSubmitOtp() {
        val otpEntity = shareState.value.otpEntity ?: return
        viewModelScope.launch {
            submitOtpCodeUseCase.invoke(
                SubmitOtpCodeUseCase.Param(
                    otpId = otpEntity.id,
                    phoneNumber = otpEntity.phoneNumber,
                    otpCode = viewState.value.otp
                )
            ).collect { result ->
                when (result) {
                    is Result.Error -> {
                        setState {
                            it.copy(
                                loading = false,
                                alertState = AlertModelState.SnackBar(
                                    message = result.message,
                                    onActionPerformed = {
                                        setState { it.copy(loading = false) }
                                    },
                                    onDismissed = {
                                        setState { it.copy(loading = false) }
                                    })
                            )
                        }
                    }

                    Result.Loading -> {
                        setState { it.copy(loading = true) }
                    }

                    is Result.Success -> {
                        cancelCountdown()
                        setState { it.copy(loading = false) }
                        postEvent(ChangePhoneNumberOtpViewEvents.NavigateBackToPersonalInfo(result.data))
                    }
                }
            }
        }
    }


    private var countdownJob: Job? = null

    private fun startCountdown(from: Int = 60) {
        cancelCountdown()
        setState { it.copy(timer = from) }
        countdownJob = viewModelScope.launch {
            for (time in from downTo 0) {
                setState { it.copy(timer = time, enableResend = time == 0) }
                if (time > 0) delay(1000L)
            }
        }
    }

    private fun cancelCountdown() {
        countdownJob?.cancel()
    }
}