package com.pmb.transfer.presentation.transfer_search_history.viewmodel

import androidx.lifecycle.viewModelScope
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.Result
import com.pmb.transfer.domain.entity.TransactionClientBankEntity
import com.pmb.transfer.domain.use_case.AccountHistoryUseCase
import com.pmb.transfer.domain.use_case.AccountSearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class TransferSearchHistoryViewModel @Inject constructor(
    private val accountHistoryUseCase: AccountHistoryUseCase,
    private val accountSearchUseCase: AccountSearchUseCase
) :
    BaseViewModel<TransferSearchHistoryViewActions, TransferSearchHistoryViewState, TransferSearchHistoryViewEvents>(
        TransferSearchHistoryViewState()
    ) {
    init {
        fetchTransferHistories()
    }

    override fun handle(action: TransferSearchHistoryViewActions) {
        when (action) {
            TransferSearchHistoryViewActions.ClearAlert -> setState { it.copy(alertState = null) }
            is TransferSearchHistoryViewActions.SearchAccounts -> handleSearchAccounts(action.value)
            is TransferSearchHistoryViewActions.SelectAccount -> handleSelectAccount(action.item)
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
                                accounts = result.data
                            )
                        }
                    }
                }
            }
        }
    }

    private fun handleSelectAccount(item: TransactionClientBankEntity) {
        postEvent(TransferSearchHistoryViewEvents.TransferDestinationAmount(item))
    }

    private fun handleSearchAccounts(value: String) {
        setState { it.copy(query = value) }
        if (value.isEmpty()) {
            setState { it.copy(accounts = emptyList()) }
            fetchTransferHistories()
            return
        }
        if (value.length < 3) return

        viewModelScope.launch {
            accountSearchUseCase.invoke(AccountSearchUseCase.Params(value)).collect { result ->
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
                                accounts = result.data
                            )
                        }
                    }
                }
            }
        }
    }
}