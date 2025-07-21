package com.pmb.transfer.presentation.transfer_verify_card_info.viewmodel

import androidx.lifecycle.viewModelScope
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.Result
import com.pmb.transfer.domain.entity.CardVerificationEntity
import com.pmb.transfer.domain.use_case.TransferResendVerifyCardInfoUseCase
import com.pmb.transfer.domain.use_case.TransferVerifyCardInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransferVerifyCardInfoViewModel @Inject constructor(
    private val transferResendVerifyCardInfoUseCase: TransferResendVerifyCardInfoUseCase,
    private val transferVerifyCardInfoUseCase: TransferVerifyCardInfoUseCase
) :
    BaseViewModel<TransferVerifyCardInfoViewActions, TransferVerifyCardInfoViewState, TransferVerifyCardInfoViewEvents>(
        TransferVerifyCardInfoViewState()
    ) {
    private val _verificationInfo = MutableStateFlow<CardVerificationEntity?>(null)

    override fun handle(action: TransferVerifyCardInfoViewActions) {
        when (action) {
            TransferVerifyCardInfoViewActions.ClearAlert -> setState { it.copy(alertState = null) }
            TransferVerifyCardInfoViewActions.SubmitCardInfo -> handleSubmitOtp()
            is TransferVerifyCardInfoViewActions.ResendCardInfo -> handleResendOtp(action)
            is TransferVerifyCardInfoViewActions.UpdateCardInfo -> handleUpdateCardBank(action)
            is TransferVerifyCardInfoViewActions.TransferVerify -> handleTransferId(action)
            is TransferVerifyCardInfoViewActions.UpdateDyPassword -> setState { it.copy(password = action.dyPassword) }
            is TransferVerifyCardInfoViewActions.UpdateCvv2 -> setState { it.copy(cvv2 = action.cvv2) }
            TransferVerifyCardInfoViewActions.StartTimer -> startCountdown(
                _verificationInfo.value?.duration ?: 120
            )

            TransferVerifyCardInfoViewActions.CancelTimer -> cancelCountdown()
        }
    }

    private fun handleTransferId(action: TransferVerifyCardInfoViewActions.TransferVerify) {
        _verificationInfo.value = action.verificationInfo
        startCountdown(action.verificationInfo.duration)
    }

    private fun handleResendOtp(action: TransferVerifyCardInfoViewActions.ResendCardInfo) {
        val params =
            TransferResendVerifyCardInfoUseCase.Params(id = _verificationInfo.value?.id ?: return)

        setState { it.copy(enableResend = false) }

        viewModelScope.launch {
            transferResendVerifyCardInfoUseCase.invoke(params).collect { result ->
                when (result) {
                    is Result.Error -> {
                        setState {
                            it.copy(
                                loading = false,
                                alertState = AlertModelState.Dialog(
                                    title = "خطا",
                                    description = " ${result.message}",
                                    positiveButtonTitle = "تایید",
                                    onPositiveClick = {
                                        setState { state -> state.copy(alertState = null) }
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
            transferVerifyCardInfoUseCase.invoke(
                TransferVerifyCardInfoUseCase.Params(
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
                                    alertState = AlertModelState.Dialog(
                                        title = "خطا",
                                        description = " ${result.message}",
                                        positiveButtonTitle = "تایید",
                                        onPositiveClick = {
                                            setState { state -> state.copy(alertState = null) }
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
                            cancelCountdown()
                            postEvent(TransferVerifyCardInfoViewEvents.NavigateToReceipt(receipt = result.data))
                        }
                    }
                }
        }
    }

    private fun handleUpdateCardBank(action: TransferVerifyCardInfoViewActions.UpdateCardInfo) {
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