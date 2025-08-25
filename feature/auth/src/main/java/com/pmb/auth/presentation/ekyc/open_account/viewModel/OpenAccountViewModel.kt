package com.pmb.auth.presentation.ekyc.open_account.viewModel

import androidx.lifecycle.viewModelScope
import com.pmb.auth.domain.ekyc.open_account.useCase.OpenAccountUseCase
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OpenAccountViewModel @Inject constructor(
    initialState: OpenAccountViewState,
    private val openAccountUseCase: OpenAccountUseCase
) : BaseViewModel<OpenAccountViewActions, OpenAccountViewState, OpenAccountViewEvents>(initialState) {
    override fun handle(action: OpenAccountViewActions) {
        when (action) {
            OpenAccountViewActions.ClearAlert -> setState { it.copy(loading = false) }
            is OpenAccountViewActions.OpenAccountConfirm -> {
                handleOpenAccountConfirmation()
            }
            is OpenAccountViewActions.SelectRules ->{
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

    private fun handleOpenAccountConfirmation() {
        viewModelScope.launch {
            openAccountUseCase.invoke(Unit).collect { result ->
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
                        postEvent(OpenAccountViewEvents.OpenAccountSucceed)
                    }
                }
            }
        }
    }
}