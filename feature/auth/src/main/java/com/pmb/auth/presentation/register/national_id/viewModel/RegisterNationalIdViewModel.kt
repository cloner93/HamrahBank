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
                handleRegisterNationalId()
            }

            is RegisterNationalIdViewActions.SetNationalIdSerial -> {
                handleSetNationalIdSerial(action)
            }
        }
    }

    private fun handleSetNationalIdSerial(serial: RegisterNationalIdViewActions.SetNationalIdSerial) {
        setState { it.copy(nationalSerialId = serial.nationalIdSerial) }
    }

    private fun handleRegisterNationalId() {
        viewModelScope.launch {
            repository.invoke(
                RegisterNationalIdRequest(viewState.value.nationalSerialId ?: "")
            ).collect { result ->
                when (result) {
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