package com.pmb.transfer.presentation.transfer.viewmodel

import androidx.lifecycle.viewModelScope
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.Result
import com.pmb.transfer.domain.entity.TransactionClientBankEntity
import com.pmb.transfer.domain.use_case.AccountFavoritesUseCase
import com.pmb.transfer.domain.use_case.AccountHistoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransferViewModel @Inject constructor(
    private val accountFavoritesUseCase: AccountFavoritesUseCase,
    private val accountHistoryUseCase: AccountHistoryUseCase
) :
    BaseViewModel<TransferViewActions, TransferViewState, TransferViewEvents>(TransferViewState()) {
    init {
        fetchTransferFavorites()
        fetchTransferHistories()
    }

    override fun handle(action: TransferViewActions) {
        when (action) {
            is TransferViewActions.NavigateToDestinationInput -> handleDestinationInput()
            TransferViewActions.ClearAlert -> setState { it.copy(alertState = null) }
            is TransferViewActions.SelectAccount -> handleSelectAccount(action.item)
        }
    }

    private fun fetchTransferFavorites() {
        viewModelScope.launch {
            accountFavoritesUseCase.invoke(Unit).collect { result ->
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
                        setState {
                            it.copy(
                                loading = false,
                                favoriteAccounts = result.data
                            )
                        }
                    }
                }
            }
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
        postEvent(TransferViewEvents.TransferDestinationAmount(item))
    }

    private fun handleDestinationInput() {
        postEvent(TransferViewEvents.TransferDestination)
    }
}