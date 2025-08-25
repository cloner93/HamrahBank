package com.pmb.transfer.presentation.transfer_edit_destination.viewmodel

import androidx.lifecycle.viewModelScope
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.Result
import com.pmb.transfer.domain.entity.TransactionClientBankEntity
import com.pmb.transfer.domain.param.AccountFavoriteToggleParam
import com.pmb.transfer.domain.use_case.AccountFavoriteToggleUseCase
import com.pmb.transfer.domain.use_case.AccountHistoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransferEditDestinationViewModel @Inject constructor(
    private val accountHistoryUseCase: AccountHistoryUseCase,
    private val accountFavoriteToggleUseCase: AccountFavoriteToggleUseCase
) :
    BaseViewModel<TransferEditDestinationViewActions, TransferEditDestinationViewState, TransferEditDestinationViewEvents>(
        TransferEditDestinationViewState()
    ) {
    init {
        fetchTransferHistories()
    }

    override fun handle(action: TransferEditDestinationViewActions) {
        when (action) {
            is TransferEditDestinationViewActions.ChangeFavoriteStatus -> handleChangeFavoriteStatus(
                action.newStatus,
                action.item
            )

            TransferEditDestinationViewActions.ClearAlert -> setState { it.copy(alertState = null) }
            is TransferEditDestinationViewActions.SelectAccount -> handleSelectAccount(action.item)
        }
    }

    private fun fetchTransferHistories() {
        viewModelScope.launch {
            accountHistoryUseCase.invoke(Unit).collect { result ->
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
                                transactionClientBanks = result.data
                            )
                        }
                    }
                }
            }
        }
    }

    private fun handleSelectAccount(item: TransactionClientBankEntity) {
        postEvent(TransferEditDestinationViewEvents.TransferDestinationAmount(item))
    }

    private fun handleChangeFavoriteStatus(newStatus: Boolean, item: TransactionClientBankEntity) {
        viewModelScope.launch {
            accountFavoriteToggleUseCase(
                AccountFavoriteToggleParam(
                    newStatus = newStatus,
                    item = item
                )
            ).collect { result ->
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
                                transactionClientBanks = result.data,
                            )
                        }
                    }
                }
            }
        }
    }

}