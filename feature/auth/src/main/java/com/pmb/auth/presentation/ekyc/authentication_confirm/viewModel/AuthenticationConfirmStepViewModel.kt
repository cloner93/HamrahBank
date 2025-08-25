package com.pmb.auth.presentation.ekyc.authentication_confirm.viewModel

import androidx.lifecycle.viewModelScope
import com.pmb.auth.domain.ekyc.authentication_confirm_step.useCase.AuthenticationConfirmStepUseCase
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewEvent
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthenticationConfirmStepViewModel @Inject constructor(
    initialState: AuthenticationConfirmStepViewState,
    private val authenticationConfirmStepUseCase: AuthenticationConfirmStepUseCase
) : BaseViewModel<AuthenticationConfirmStepViewActions, AuthenticationConfirmStepViewState, BaseViewEvent>(
    initialState
) {
    override fun handle(action: AuthenticationConfirmStepViewActions) {
        when (action) {
            AuthenticationConfirmStepViewActions.ClearAlert -> {
                setState { it.copy(loading = false) }
            }

            AuthenticationConfirmStepViewActions.LoadAuthenticationStep -> {
                handleLoadAuthenticationConfirmStep()
            }
        }
    }

    private fun handleLoadAuthenticationConfirmStep() {
        viewModelScope.launch {
            authenticationConfirmStepUseCase.invoke(Unit).collect { result ->
                when (result) {
                    is Result.Loading -> {
                        setState {
                            it.copy(
                                loading = true
                            )
                        }
                    }

                    is Result.Error -> {
                        setState {
                            it.copy(
                                loading = false,
                                alertModelState =AlertModelState.Dialog(
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

                    is Result.Success -> {
                        setState {
                            it.copy(
                                loading = false,
                                data = result.data
                            )
                        }
                    }
                }
            }
        }
    }

    init {
        handle(AuthenticationConfirmStepViewActions.LoadAuthenticationStep)
    }
}