package com.pmb.transfer.presentation.transfer_edit_destination.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.Result
import com.pmb.domain.usecae.favorite.FetchRecentTransferFavoriteAccountsUseCase
import com.pmb.domain.usecae.favorite.InsertFavoriteAccountParams
import com.pmb.domain.usecae.favorite.InsertFavoriteAccountUseCase
import com.pmb.transfer.domain.entity.TransactionClientBankEntity
import com.pmb.transfer.domain.entity.toEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransferEditDestinationViewModel @Inject constructor(
    private val insertFavoriteAccountUseCase: InsertFavoriteAccountUseCase,
    private val fetchRecentFavoriteTransferFavoriteAccountUseCase: FetchRecentTransferFavoriteAccountsUseCase
) : BaseViewModel<TransferEditDestinationViewActions, TransferEditDestinationViewState, TransferEditDestinationViewEvents>(
    TransferEditDestinationViewState()
) {
    init {
        fetchTransferHistories()
    }

    override fun handle(action: TransferEditDestinationViewActions) {
        when (action) {
            is TransferEditDestinationViewActions.ChangeFavoriteStatus -> handleChangeFavoriteStatus(
                action.item
            )

            TransferEditDestinationViewActions.ClearAlert -> setState { it.copy(alertState = null) }
            is TransferEditDestinationViewActions.SelectAccount -> handleSelectAccount(action.item)
        }
    }

    private fun fetchTransferHistories() {
        viewModelScope.launch {
            fetchRecentFavoriteTransferFavoriteAccountUseCase.invoke(Unit).collect { result ->
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
                                    })
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
                                transactionClientBanks = result.data.map {
                                    it.toEntity().copy(favorite = false)
                                })
                        }
                    }
                }
            }
        }
    }

    private fun handleSelectAccount(item: TransactionClientBankEntity) {
        postEvent(TransferEditDestinationViewEvents.TransferDestinationAmount(item))
    }

    private fun handleChangeFavoriteStatus(item: TransactionClientBankEntity) {
        viewModelScope.launch {
            val account = with(item.clientBankEntity) {
                when {
                    accountNumber.isNotEmpty() -> accountNumber
                    cardNumber.isNotEmpty() -> cardNumber
                    iban.isNotEmpty() -> iban
                    else -> ""
                }
            }
            insertFavoriteAccountUseCase(
                InsertFavoriteAccountParams(
                    ownerDescription = item.clientBankEntity.name, number = account
                )
            ).collect { result ->
                Log.d("Masoud Tag", "handleChangeFavoriteStatus: $result")
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
                                    })
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
                            )
                        }
                        postEvent(TransferEditDestinationViewEvents.NavigateUp)
                    }
                }
            }
        }
    }

}