package com.pmb.auth.presentaion.first_login.viewModel

import androidx.lifecycle.viewModelScope
import com.pmb.auth.domain.first_login.entity.FirstLoginStepRequest
import com.pmb.auth.domain.first_login.useCase.FirstLoginUseCase
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.Result
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
            is FirstLoginViewActions.FirstLoginStepConfirm -> handleFirstLoginStepConfirm(action)
        }
    }

    private fun handleFirstLoginStepConfirm(action: FirstLoginViewActions.FirstLoginStepConfirm) {
        viewModelScope.launch {
            firstLoginUseCase.invoke(
                FirstLoginStepRequest(
                    mobileNumber = action.mobileNumber,
                    userName = action.userName,
                    password = action.password
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