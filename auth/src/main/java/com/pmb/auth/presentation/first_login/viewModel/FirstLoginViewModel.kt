package com.pmb.auth.presentation.first_login.viewModel

import androidx.lifecycle.viewModelScope
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.Result
import com.pmb.domain.usecae.auth.FirstLoginStepRequest
import com.pmb.domain.usecae.auth.FirstLoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FirstLoginViewModel @Inject constructor(
    initialState: FirstLoginViewState, private val firstLoginUseCase: FirstLoginUseCase
) : BaseViewModel<FirstLoginViewActions, FirstLoginViewState, FirsLoginViewEvents>(initialState) {
    override fun handle(action: FirstLoginViewActions) {
        when (action) {
            FirstLoginViewActions.ClearAlert -> setState { it.copy(loading = false) }
            is FirstLoginViewActions.FirstLoginStepConfirm -> handleFirstLoginStepConfirm()
            is FirstLoginViewActions.UpdatePassword -> setState { it.copy(password = action.value) }
            is FirstLoginViewActions.UpdatePhoneNumber -> setState { it.copy(phoneNumber = action.value) }
            is FirstLoginViewActions.UpdateUsername -> setState { it.copy(username = action.value) }
        }
    }

    private fun handleFirstLoginStepConfirm() {
        viewModelScope.launch {
            firstLoginUseCase.invoke(
                FirstLoginStepRequest(
                    mobileNumber = viewState.value.phoneNumber,
                    userName = viewState.value.username,
                    password = viewState.value.password
                )
            ).collect { result ->
                when (result) {
                    is Result.Success -> {
                        setState {
                            it.copy(loading = false)
                        }
                        postEvent(FirsLoginViewEvents.FirstLoginStepSucceed)
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