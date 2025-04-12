package com.pmb.transfer.presentation.transfer_confirm.viewmodel

import androidx.lifecycle.viewModelScope
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.Result
import com.pmb.transfer.domain.use_case.TransferConfirmUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransferConfirmViewModel @Inject constructor(
    private val transferConfirmUseCase: TransferConfirmUseCase
) :
    BaseViewModel<TransferConfirmViewActions, TransferConfirmViewState, TransferConfirmViewEvents>(
        TransferConfirmViewState()
    ) {
    override fun handle(action: TransferConfirmViewActions) {
        when (action) {
            TransferConfirmViewActions.ClearAlert -> setState { it.copy(alertState = null) }
            is TransferConfirmViewActions.SubmitTransferData -> handleSubmitTransferData(action)
        }
    }

    private fun handleSubmitTransferData(action: TransferConfirmViewActions.SubmitTransferData) {
        viewModelScope.launch {
            transferConfirmUseCase.invoke(TransferConfirmUseCase.Params(value = "value"))
                .collect { result ->
                    when (result) {
                        is Result.Error -> {
                            setState {
                                it.copy(
                                    loading = false,
                                    alertState = AlertModelState.SnackBar(
                                        message = result.message,
                                        onActionPerformed = {
                                            setState { it.copy(loading = false) }
                                        },
                                        onDismissed = {
                                            setState { it.copy(loading = false) }
                                        }
                                    )
                                )
                            }
                        }

                        Result.Loading -> {
                            setState { it.copy(loading = true) }
                        }

                        is Result.Success -> {
                            setState { it.copy(loading = false) }
                            postEvent(
                                TransferConfirmViewEvents.NavigateToOtp(
                                    id = result.data.id,
                                    duration = result.data.duration
                                )
                            )
                        }
                    }
                }
        }
    }
}