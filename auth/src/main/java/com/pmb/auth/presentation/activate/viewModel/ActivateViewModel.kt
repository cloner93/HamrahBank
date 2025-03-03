package com.pmb.auth.presentation.activate.viewModel

import androidx.lifecycle.viewModelScope
import com.pmb.auth.domain.activate.entity.ActivateParams
import com.pmb.auth.domain.activate.useCase.ActivationUserUseCase
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActivateViewModel @Inject constructor(
    initialState: ActivateViewState,
    private val activationUserUseCase: ActivationUserUseCase
) : BaseViewModel<ActivateViewActions, ActivateViewState, ActivateViewEvents>(initialState) {
    override fun handle(action: ActivateViewActions) {
        when (action) {
            ActivateViewActions.ClearAlert -> setState { it.copy(loading = false) }
            is ActivateViewActions.ActiveUser -> handleActivationUser(action)
        }
    }

    private fun handleActivationUser(action: ActivateViewActions.ActiveUser) {
        viewModelScope.launch {
            activationUserUseCase.invoke(
                ActivateParams(
                    userName = action.userName,
                    mobileNumber = action.mobileNumber,
                    password = action.password,
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
                        postEvent(ActivateViewEvents.ActiveUserSucceed)
                    }
                }
            }
        }
    }

}