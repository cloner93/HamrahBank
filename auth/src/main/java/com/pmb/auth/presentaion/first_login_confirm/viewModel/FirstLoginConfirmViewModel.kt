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
    override fun handle(action: FirstLoginConfirmViewActions) {
        when (action) {
            FirstLoginConfirmViewActions.ClearAlert -> setState { it.copy(loading = false) }
            is FirstLoginConfirmViewActions.ConfirmFirstLogin -> handleConfirmFirstLogin(action)
            is FirstLoginConfirmViewActions.ResendFirstLoginInfo -> handleResendFirstLoginInfo(
                action
            )
        }
    }
    private fun handleConfirmFirstLogin(action:FirstLoginConfirmViewActions.ConfirmFirstLogin){
        viewModelScope.launch {
            firstLoginConfirmUseCase.invoke(
                SendOtpRequest(
                    mobileNumber = action.mobileNumber,
                    otp = action.otpCode
                )
            ).collect{result->
                when(result){
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
                            it.copy(loading = false)
                        }
                        postEvent(FirstLoginConfirmViewEvents.FirstLoginConfirmResend)
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
}