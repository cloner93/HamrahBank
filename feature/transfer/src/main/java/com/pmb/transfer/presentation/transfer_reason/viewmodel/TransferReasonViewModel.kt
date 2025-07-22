package com.pmb.transfer.presentation.transfer_reason.viewmodel

import androidx.lifecycle.viewModelScope
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.Result
import com.pmb.transfer.domain.use_case.TransferReasonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransferReasonViewModel @Inject constructor(
    private val transferReasonUseCase: TransferReasonUseCase
) :
    BaseViewModel<TransferReasonViewActions, TransferReasonViewState, TransferReasonViewEvents>(
        TransferReasonViewState()
    ) {
    init {
        fetchReasons()
    }

    override fun handle(action: TransferReasonViewActions) {
        when (action) {
            TransferReasonViewActions.ClearAlert -> setState { it.copy(alertState = null) }
            is TransferReasonViewActions.SelectReason -> {
                setState { it.copy(selectedReason = action.reason) }
                postEvent(TransferReasonViewEvents.NavigateUp(action.reason))
            }
        }
    }

    private fun fetchReasons() {
        viewModelScope.launch {
            transferReasonUseCase.invoke(Unit).collect { result ->
                when (result) {
                    is Result.Error -> {
                        setState {
                            it.copy(
                                loading = false,
                                alertState = AlertModelState.Dialog(
                                    title = "خطا",
                                    description = " ${result.message}",
                                    positiveButtonTitle = "تایید",
                                    onPositiveClick = {
                                        setState { state -> state.copy(alertState = null) }
                                    }
                                )
                            )
                        }
                    }

                    Result.Loading -> {
                        setState { it.copy(loading = true) }
                    }

                    is Result.Success -> {
                        setState {
                            it.copy(
                                loading = false,
                                reasons = result.data
                            )
                        }
                    }
                }
            }
        }
    }
}