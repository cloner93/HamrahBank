package com.pmb.auth.presentaion.foget_password.viewmodel

import androidx.lifecycle.viewModelScope
import com.pmb.auth.domain.forget_password.usecase.ForgetPasswordParams
import com.pmb.auth.domain.forget_password.usecase.ForgetPasswordUseCase
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForgetPasswordViewModel @Inject constructor(
    initialState: ForgetPasswordViewState, private val forgetPasswordUseCase: ForgetPasswordUseCase
) : BaseViewModel<ForgetPasswordViewActions, ForgetPasswordViewState, ForgetPasswordViewEvents>(
    initialState
) {
    override fun handle(action: ForgetPasswordViewActions) {
        when (action) {
            ForgetPasswordViewActions.ClearAlert -> setState { it.copy(loading = false) }
            is ForgetPasswordViewActions.ResetPassword -> handleResetPassword(action)
        }
    }

    private fun handleResetPassword(action: ForgetPasswordViewActions.ResetPassword) {
        viewModelScope.launch {
            forgetPasswordUseCase.invoke(
                ForgetPasswordParams(
                    mobileNumber = action.mobileNumber,
                    nationalId = action.nationalId,
                    password = action.password
                )
            ).collect { result ->
                when (result) {
                    is Result.Error -> {
                        setState {
                            it.copy(
                                loading = false,
                                alert = AlertModelState.SnackBar(message = result.message,
                                    onActionPerformed = {
                                        setState { state -> state.copy(alert = null) }
                                    },
                                    onDismissed = {
                                        setState { state -> state.copy(alert = null) }
                                    })
                            )
                        }
                    }

                    Result.Loading -> setState { it.copy(loading = true) }
                    is Result.Success -> {
                        setState {
                            it.copy(loading = false)
                        }
                        postEvent(ForgetPasswordViewEvents.ResetPasswordSuccess)
                    }
                }
            }
        }
    }
}