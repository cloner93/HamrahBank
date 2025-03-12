package com.pmb.auth.presentation.activation.activation_tax_details.viewModel

import androidx.lifecycle.viewModelScope
import com.pmb.auth.domain.activation.tax_details.useCase.SendActivationTaxDetailsUseCase
import com.pmb.auth.domain.ekyc.fee_details.useCase.GetFeeDetailsUseCase
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActivationTaxDetailsViewModel @Inject constructor(
    initialState: ActivationTaxDetailsViewState,
    private var getFeeDetailsUseCase: GetFeeDetailsUseCase,
    private var sendActivationTaxDetailsUseCase: SendActivationTaxDetailsUseCase
) : BaseViewModel<ActivationTaxDetailsViewActions, ActivationTaxDetailsViewState, ActivationTaxDetailsViewEvents>(
    initialState
) {
    override fun handle(action: ActivationTaxDetailsViewActions) {
        when (action) {
            is ActivationTaxDetailsViewActions.ClearAlert -> {
                setState {
                    it.copy(isLoading = false)
                }
            }

            is ActivationTaxDetailsViewActions.LoadActivationTaxDetails -> {
                getFeeDetails()
            }

            is ActivationTaxDetailsViewActions.SendActivationTaxDetailsData -> {
                handleSendActivationTaxDetails(action)
            }
        }
    }

    private fun handleSendActivationTaxDetails(action: ActivationTaxDetailsViewActions.SendActivationTaxDetailsData) {
        viewModelScope.launch {
            sendActivationTaxDetailsUseCase.invoke(action.accountNumber).collect { result ->
                when (result) {
                    is Result.Error -> {
                        setState {
                            it.copy(
                                isLoading = false,
                                alertModelState = AlertModelState.SnackBar(
                                    message = result.message,
                                    onActionPerformed = {
                                        setState {
                                            it.copy(
                                                isLoading = false
                                            )
                                        }
                                    },
                                    onDismissed = {
                                        setState {
                                            it.copy(
                                                isLoading = false
                                            )
                                        }
                                    }
                                )
                            )
                        }
                    }

                    is Result.Success -> {
                        setState {
                            it.copy(
                                isLoading = false,
                            )
                        }
                        postEvent(ActivationTaxDetailsViewEvents.ConfirmTaxDetails)
                    }

                    is Result.Loading -> {
                        setState {
                            it.copy(isLoading = true)
                        }
                    }
                }
            }
        }
    }

    private fun getFeeDetails() {
        viewModelScope.launch {
            getFeeDetailsUseCase.invoke(Unit).collect { result ->
                when (result) {
                    is Result.Error -> {
                        setState {
                            it.copy(
                                isLoading = false,
                                alertModelState = AlertModelState.SnackBar(
                                    message = result.message,
                                    onActionPerformed = {
                                        setState {
                                            it.copy(
                                                isLoading = false
                                            )
                                        }
                                    },
                                    onDismissed = {
                                        setState {
                                            it.copy(
                                                isLoading = false
                                            )
                                        }
                                    }
                                )
                            )
                        }
                    }

                    is Result.Success -> {
                        setState {
                            it.copy(
                                isLoading = false,
                                data = result.data
                            )
                        }
                    }

                    is Result.Loading -> {
                        setState {
                            it.copy(isLoading = true)
                        }
                    }
                }
            }
        }
    }

    init {
        handle(ActivationTaxDetailsViewActions.LoadActivationTaxDetails)
    }
}