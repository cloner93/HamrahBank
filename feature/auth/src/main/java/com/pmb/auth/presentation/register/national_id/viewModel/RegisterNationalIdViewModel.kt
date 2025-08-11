package com.pmb.auth.presentation.register.national_id.viewModel

import androidx.lifecycle.viewModelScope
import com.pmb.auth.domain.register.national_id.entity.RegisterNationalIdRequest
import com.pmb.auth.domain.register.national_id.useCase.RegisterNationalIdUseCase
import com.pmb.auth.presentation.register.account_opening.viewModel.OpeningAccountViewEvents
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.Result
import com.pmb.core.utils.setMobileValidator
import com.pmb.core.utils.setNationalCodeValidator
import com.pmb.domain.usecae.auth.openAccount.GenerateCodeParams
import com.pmb.domain.usecae.auth.openAccount.GenerateCodeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterNationalIdViewModel @Inject constructor(
    initialState: RegisterNationalIdViewState,
    private val generateCodeUseCase: GenerateCodeUseCase,
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

            is RegisterNationalIdViewActions.SetNationalIdSerial -> {
                handleSetNationalIdSerial(action)
            }
        }
    }

    private fun handleSetNationalIdSerial(serial: RegisterNationalIdViewActions.SetNationalIdSerial) {
        setState { it.copy(nationalSerialId = serial.nationalIdSerial) }
    }

    private fun handleRegisterNationalId(action: RegisterNationalIdViewActions.RegisterNationalIdSerialServices) {
        viewModelScope.launch {
            generateCodeUseCase.invoke(
                GenerateCodeParams(
                    nationalCode = action.nationalCode.setNationalCodeValidator(),
                    mobileNo = action.mobileNo.setMobileValidator(),
                    birthDate = action.birthDate
                )
            ).collectLatest { result ->
                when (result) {
                    is Result.Loading -> {
                        setState {
                            it.copy(
                                isLoading = true
                            )
                        }
                    }

                    is Result.Success -> {
                        setState {
                            it.copy(
                                isLoading = false
                            )
                        }
                        postEvent(RegisterNationalIdViewEvents.RegisterNationalSucceed)
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
                }
            }
        }
    }

}