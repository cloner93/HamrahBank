package com.pmb.auth.presentation.register.checkPostalCode.viewModel

import androidx.lifecycle.viewModelScope
import com.pmb.auth.domain.register.check_postal_code.entity.CheckPostalCodeRequest
import com.pmb.auth.domain.register.check_postal_code.entity.SendAddressRequest
import com.pmb.auth.domain.register.check_postal_code.useCase.CheckPostalCodeUseCase
import com.pmb.auth.domain.register.check_postal_code.useCase.SendAddressUseCase
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CheckPostalCodeViewModel @Inject constructor(
    initialState: CheckPostalCodeViewState,
    private val checkPostalCodeUseCase: CheckPostalCodeUseCase,
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
            checkPostalCodeUseCase.invoke(
                CheckPostalCodeRequest(
                    postalCode = action.postalCode,
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