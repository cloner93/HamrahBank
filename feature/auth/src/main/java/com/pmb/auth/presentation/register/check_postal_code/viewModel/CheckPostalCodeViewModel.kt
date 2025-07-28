package com.pmb.auth.presentation.register.check_postal_code.viewModel

import androidx.lifecycle.viewModelScope
import com.pmb.auth.domain.register.check_postal_code.entity.CheckPostalCodeRequest
import com.pmb.auth.domain.register.check_postal_code.entity.SendAddressRequest
import com.pmb.auth.domain.register.check_postal_code.useCase.CheckPostalCodeUseCase
import com.pmb.auth.domain.register.check_postal_code.useCase.SendAddressUseCase
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.Result
import com.pmb.domain.usecae.auth.openAccount.CheckPostCodeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CheckPostalCodeViewModel @Inject constructor(
    initialState: CheckPostalCodeViewState,
    private val checkPostCodeUseCase: CheckPostCodeUseCase,
    private val sendAddressUseCase: SendAddressUseCase
) : BaseViewModel<CheckPostalCodeViewActions, CheckPostalCodeViewState, CheckPostalCodeViewEvents>(
    initialState
) {
    override fun handle(action: CheckPostalCodeViewActions) {
        when (action) {
            is CheckPostalCodeViewActions.ClearAlert -> {
                setState {
                    it.copy(
                        isLoading = false
                    )
                }
            }

            is CheckPostalCodeViewActions.CheckPostalCode -> {
                handlePostalCode(action)
            }

            is CheckPostalCodeViewActions.CheckAddress -> {
                handleCheckAddress(action)
            }
        }
    }

    private fun handleCheckAddress(action: CheckPostalCodeViewActions.CheckAddress) {
        viewModelScope.launch {
            sendAddressUseCase.invoke(
                SendAddressRequest(
                    postalCode = action.postalCode,
                    address = action.address
                )
            ).collect { result ->
                when (result) {
                    is Result.Loading -> {
                        setState {
                            it.copy(
                                isLoading = true
                            )
                        }
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

                    is Result.Success -> {
                        setState {
                            it.copy(isLoading = false)
                        }
                        postEvent(CheckPostalCodeViewEvents.CheckAddressSucceed)
                    }
                }
            }
        }
    }

    private fun handlePostalCode(action: CheckPostalCodeViewActions.CheckPostalCode) {
        viewModelScope.launch {
            checkPostCodeUseCase.invoke(
                action.postalCode.toInt()
            ).collect { result ->
                when (result) {
                    is Result.Loading -> {
                        setState {
                            it.copy(
                                isLoading = true
                            )
                        }
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

                    is Result.Success -> {
                        setState {
                            it.copy(isLoading = false)
                        }
                        postEvent(CheckPostalCodeViewEvents.CheckPostalCode(address = result.data.address))
                    }
                }
            }
        }
    }

}