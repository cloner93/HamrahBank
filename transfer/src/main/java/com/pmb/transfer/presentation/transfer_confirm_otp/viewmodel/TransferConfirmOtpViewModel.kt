package com.pmb.transfer.presentation.transfer_confirm_otp.viewmodel

import androidx.lifecycle.viewModelScope
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.Result
import com.pmb.transfer.domain.entity.TransferConfirmEntity
import com.pmb.transfer.domain.use_case.TransferResendOtpUseCase
import com.pmb.transfer.domain.use_case.TransferSubmitOtpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransferConfirmOtpViewModel @Inject constructor(
    private val transferResendOtpUseCase: TransferResendOtpUseCase,
    private val transferSubmitOtpUseCase: TransferSubmitOtpUseCase
) :
    BaseViewModel<TransferConfirmOtpViewActions, TransferConfirmOtpViewState, TransferConfirmOtpViewEvents>(
        TransferConfirmOtpViewState()
    ) {
    private val _transferConfirm = MutableStateFlow<TransferConfirmEntity?>(null)

    override fun handle(action: TransferConfirmOtpViewActions) {
        when (action) {
            TransferConfirmOtpViewActions.ClearAlert -> setState { it.copy(alertState = null) }
            TransferConfirmOtpViewActions.SubmitOtp -> handleSubmitOtp()
            is TransferConfirmOtpViewActions.ResendOtp -> handleResendOtp(action)
            is TransferConfirmOtpViewActions.UpdateCardBank -> handleUpdateCardBank(action)
            is TransferConfirmOtpViewActions.TransferConfirm -> handleTransferId(action)
            is TransferConfirmOtpViewActions.UpdateDyPassword -> setState { it.copy(password = action.dyPassword) }
            is TransferConfirmOtpViewActions.UpdateCvv2 -> setState { it.copy(cvv2 = action.cvv2) }
            TransferConfirmOtpViewActions.StartTimer -> startCountdown(
                _transferConfirm.value?.duration ?: 120
            )
        }
    }

    private fun handleTransferId(action: TransferConfirmOtpViewActions.TransferConfirm) {
        _transferConfirm.value = action.transferConfirm
        startCountdown(action.transferConfirm.duration)
    }

    private fun handleResendOtp(action: TransferConfirmOtpViewActions.ResendOtp) {
        setState { it.copy(enableResend = false) }
        viewModelScope.launch {
            transferResendOtpUseCase.invoke(
                TransferResendOtpUseCase.Params(
                    id = _transferConfirm.value?.id ?: ""
                )
            )
                .collect { result ->
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
                                        }
                                    )
                                )
                            }
                        }

                        Result.Loading -> {
                            setState { it.copy(loading = true) }
                        }

                        is Result.Success -> {
                            setState { it.copy(loading = false) }
                            startCountdown(result.data.duration)
                        }
                    }
                }
        }
    }

    private fun handleSubmitOtp() {
        viewModelScope.launch {
            transferSubmitOtpUseCase.invoke(
                TransferSubmitOtpUseCase.Params(
                    id = "1234",
                    cvv2 = viewState.value.cvv2,
                    password = viewState.value.password
                )
            )
                .collect { result ->
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
                                        }
                                    )
                                )
                            }
                        }

                        Result.Loading -> {
                            setState { it.copy(loading = true) }
                        }

                        is Result.Success -> {
                            setState { it.copy(loading = false) }
                            postEvent(TransferConfirmOtpViewEvents.NavigateToReceipt(receipt = result.data))
                        }
                    }
                }
        }
    }

    private fun handleUpdateCardBank(action: TransferConfirmOtpViewActions.UpdateCardBank) {
        setState { it.copy(cardBank = action.cardBank) }
    }


    private var countdownJob: Job? = null

    private fun startCountdown(from: Int = 60) {
        cancelCountdown()
        setState { it.copy(timer = from, enableResend = false) }
        countdownJob = viewModelScope.launch {
            for (time in from downTo 0) {
                setState { it.copy(timer = time, enableResend = false) }
                if (time > 0) delay(1000L)
            }
            setState { it.copy(enableResend = true) }
        }
    }

    private fun cancelCountdown() {
        countdownJob?.cancel()
    }
}