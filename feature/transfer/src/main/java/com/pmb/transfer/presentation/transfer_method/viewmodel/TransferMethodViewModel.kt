package com.pmb.transfer.presentation.transfer_method.viewmodel

import androidx.lifecycle.viewModelScope
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.Result
import com.pmb.transfer.domain.entity.TransferMethodEntity
import com.pmb.transfer.domain.use_case.TransferMethodUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransferMethodViewModel @Inject constructor(
    private val transferMethodUseCase: TransferMethodUseCase
) : BaseViewModel<TransferMethodViewActions, TransferMethodViewState, TransferMethodViewEvents>(
    TransferMethodViewState()
) {

    override fun handle(action: TransferMethodViewActions) {
        when (action) {
            TransferMethodViewActions.ClearAlert -> setState { it.copy(alertState = null) }
            is TransferMethodViewActions.SelectPaymentType -> handleSelectPaymentType(action.transferMethod)

            is TransferMethodViewActions.SetPaymentTypes -> setState { it.copy(transferMethods = action.methods) }
        }
    }

    private fun fetchTransferMethods() {
        viewModelScope.launch {
            transferMethodUseCase.invoke(Unit).collect { result ->
                when (result) {
                    is Result.Error -> {
                        setState {
                            it.copy(
                                loading = false, alertState = AlertModelState.Dialog(
                                title = "خطا",
                                description = " ${result.message}",
                                positiveButtonTitle = "تایید",
                                onPositiveClick = {
                                    setState { state -> state.copy(alertState = null) }
                                }))
                        }
                    }

                    Result.Loading -> {
                        setState { it.copy(loading = true) }
                    }

                    is Result.Success -> {
                        setState {
                            it.copy(
                                loading = false, transferMethods = result.data
                            )
                        }
                    }
                }
            }
        }
    }

    private fun handleSelectPaymentType(transferMethod: TransferMethodEntity) {
        postEvent(TransferMethodViewEvents.NavigateToTransferConfirm(transferMethod))
    }
}