package com.pmb.auth.presentation.register.national_id.viewModel

import androidx.lifecycle.viewModelScope
import com.pmb.auth.domain.register.national_id.entity.RegisterNationalIdRequest
import com.pmb.auth.domain.register.national_id.useCase.RegisterNationalIdUseCase
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterNationalIdViewModel @Inject constructor(
    initialState: RegisterNationalIdViewState,
    private val repository: RegisterNationalIdUseCase
) : BaseViewModel<RegisterNationalIdViewActions, RegisterNationalIdViewState, RegisterNationalIdViewEvents>(
    initialState
) {
    override fun handle(action: RegisterNationalIdViewActions) {
        when (action) {
            is RegisterNationalIdViewActions.ClearAlert -> {
                setState {
                    it.copy(
                        isLoading = false
                    )
                }
            }

            is RegisterNationalIdViewActions.RegisterNationalIdSerialServices -> {
                handleRegisterNationalId(action)
            }
        }
    }

    private fun handleRegisterNationalId(action: RegisterNationalIdViewActions.RegisterNationalIdSerialServices) {
        viewModelScope.launch {
            repository.invoke(
                RegisterNationalIdRequest(action.nationalSerialId)
            ).collect { result ->
                when (result) {
                    is Result.Error -> {
                        setState {
                            it.copy(
                                isLoading = false,
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
                        setState {
                            it.copy(
                                isLoading = true
                            )
                        }
                    }

                    is Result.Success -> {
                        setState {
                            it.copy(isLoading = false)
                        }
                        postEvent(RegisterNationalIdViewEvents.RegisterNationalSucceed)
                    }
                }
            }
        }
    }

}