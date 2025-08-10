package com.pmb.transfer.presentation.transfer_edit_favorite.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.AlertType
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.Result
import com.pmb.domain.usecae.favorite.FetchAllTransferFavoriteAccountsUseCase
import com.pmb.domain.usecae.favorite.FetchFavoriteTransferFavoriteAccountsUseCase
import com.pmb.domain.usecae.favorite.RemoveFavoriteAccountParams
import com.pmb.domain.usecae.favorite.RemoveFavoriteAccountUseCase
import com.pmb.transfer.domain.entity.TransactionClientBankEntity
import com.pmb.transfer.domain.entity.toEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransferEditFavoriteViewModel @Inject constructor(
    private val fetchFavoriteTransferFavoriteAccountsUseCase: FetchFavoriteTransferFavoriteAccountsUseCase,
    private val removeFavoriteAccountUseCase: RemoveFavoriteAccountUseCase
) : BaseViewModel<TransferEditFavoriteViewActions, TransferEditFavoriteViewState, TransferEditFavoriteViewEvents>(
    TransferEditFavoriteViewState()
) {
    init {
        fetchFavoriteList()
    }

    private fun fetchFavoriteList() {
        viewModelScope.launch {
            fetchFavoriteTransferFavoriteAccountsUseCase.invoke(Unit).collect { result ->
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

                    is Result.Success -> {
                        setState {
                            it.copy(
                                loading = false,
                                accountsFavorite = result.data.map { item -> item.toEntity() })
                        }
                    }

                    is Result.Loading -> {
                        setState { it.copy(loading = true) }
                    }
                }
            }
        }
    }

    override fun handle(action: TransferEditFavoriteViewActions) {
        when (action) {
            TransferEditFavoriteViewActions.ClearAlert -> setState { it.copy(alertState = null) }
            is TransferEditFavoriteViewActions.SelectRemoveAccount -> showRemoveAccountDialog(action.accountValue)
            is TransferEditFavoriteViewActions.RemoveAccount -> handleRemoveAccount(action.accountValue)
            is TransferEditFavoriteViewActions.SelectAccount -> handleSelectAccount(action.accountValue)
        }
    }

    private fun showRemoveAccountDialog(accountValue: TransactionClientBankEntity) {
        setState {
            it.copy(
                alertState = AlertModelState.BottomSheet(
                    type = AlertType.Warning,
                    message = "آیا از حذف «${accountValue.clientBankEntity.name}» از مقاصد برگزیده اطمینان دارید؟",
                    positiveButtonTitle = "حذف",
                    negativeButtonTitle = "انصراف",
                    onPositiveClick = {
                        handle(TransferEditFavoriteViewActions.RemoveAccount(accountValue))
                        setState { newState -> newState.copy(alertState = null) }
                    },
                    onNegativeClick = {
                        setState { newState -> newState.copy(alertState = null) }
                    })
            )
        }
    }

    private fun handleSelectAccount(accountValue: TransactionClientBankEntity) {
        postEvent(TransferEditFavoriteViewEvents.NavigateToDestinationAmount(accountValue))
    }

    private fun handleRemoveAccount(accountValue: TransactionClientBankEntity) {
        viewModelScope.launch {
            val account = with(accountValue.clientBankEntity) {
                when {
                    accountNumber.isNotEmpty() -> accountNumber
                    cardNumber.isNotEmpty() -> cardNumber
                    iban.isNotEmpty() -> iban
                    else -> ""
                }
            }

            removeFavoriteAccountUseCase.invoke(
                RemoveFavoriteAccountParams(account)
            ).collect { result ->
                Log.d("Masoud Tag", "handleRemoveAccount: $result")
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

                    is Result.Success -> {
                        setState {
                            it.copy(loading = false)
                        }
                        fetchFavoriteList()
                    }

                    is Result.Loading -> {
                        setState { it.copy(loading = true) }
                    }
                }
            }
        }
    }
}