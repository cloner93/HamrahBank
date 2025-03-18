package com.pmb.auth.presentation.activation.activate.viewModel

import androidx.lifecycle.viewModelScope
import com.pmb.auth.domain.activation.activate.entity.ActivateParams
import com.pmb.auth.domain.activation.activate.useCase.ActivationUserUseCase
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActivationViewModel @Inject constructor(
    initialState: ActivationViewState,
    private val activationUserUseCase: ActivationUserUseCase
) : BaseViewModel<ActivationViewActions, ActivationViewState, ActivationViewEvents>(initialState) {
    override fun handle(action: ActivationViewActions) {
        when (action) {
            ActivationViewActions.ClearAlert -> setState { it.copy(loading = false) }
            is ActivationViewActions.ActiveUser -> handleActivationUser(action)
            is ActivationViewActions.SelectRules -> {
                handleSelectRules()
            }
        }
    }

    private fun handleSelectRules() {
        setState {
            it.copy(
                isChecked = !it.isChecked
            )
        }
    }

    private fun handleActivationUser(action: ActivationViewActions.ActiveUser) {
        viewModelScope.launch {
            activationUserUseCase.invoke(
                ActivateParams(
                    mobileNumber = action.mobileNumber,
                    nationalId = action.nationalId
                )
            ).collect { result ->
                when (result) {
                    is Result.Error -> {
                        setState {
                            it.copy(
                                loading = false,
                                alertModelState = AlertModelState.SnackBar(
                                    message = result.message,
                                    onActionPerformed = {
                                        setState {
                                            it.copy(
                                                loading = false
                                            )
                                        }
                                    },
                                    onDismissed = {
                                        setState {
                                            it.copy(
                                                loading = false
                                            )
                                        }
                                    }
                                )
                            )
                        }
                    }

                    Result.Loading -> {
                        setState {
                            it.copy(loading = true)
                        }
                    }

                    is Result.Success -> {
                        setState {
                            it.copy(
                                loading = false
                            )
                        }
                        postEvent(ActivationViewEvents.ActiveUserSucceed)
                    }
                }
            }
        }
    }

}