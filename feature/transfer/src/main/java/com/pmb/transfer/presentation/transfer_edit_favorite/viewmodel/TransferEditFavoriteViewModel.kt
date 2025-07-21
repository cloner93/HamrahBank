package com.pmb.transfer.presentation.transfer_edit_favorite.viewmodel

import androidx.lifecycle.viewModelScope
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.Result
import com.pmb.transfer.domain.entity.TransactionClientBankEntity
import com.pmb.transfer.domain.param.AccountRemoveFavoriteParam
import com.pmb.transfer.domain.use_case.AccountFavoritesUseCase
import com.pmb.transfer.domain.use_case.AccountRemoveFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransferEditFavoriteViewModel @Inject constructor(
    private val accountFavoritesUseCase: AccountFavoritesUseCase,
    private val accountRemoveFavoriteUseCase: AccountRemoveFavoriteUseCase
) :
    BaseViewModel<TransferEditFavoriteViewActions, TransferEditFavoriteViewState, TransferEditFavoriteViewEvents>(
        TransferEditFavoriteViewState()
    ) {
    init {
        fetchFavoriteList()
    }

    private fun fetchFavoriteList() {
        viewModelScope.launch {
            accountFavoritesUseCase.invoke().collect { result ->
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

                    is Result.Success -> {
                        setState {
                            it.copy(
                                loading = false,
                                accountsFavorite = result.data
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

    override fun handle(action: TransferEditFavoriteViewActions) {
        when (action) {
            TransferEditFavoriteViewActions.ClearAlert -> setState { it.copy(alertState = null) }
            is TransferEditFavoriteViewActions.RemoveAccount -> handleRemoveAccount(action.accountValue)
            is TransferEditFavoriteViewActions.SelectAccount -> handleSelectAccount(action.accountValue)
        }
    }

    private fun handleSelectAccount(accountValue: TransactionClientBankEntity) {
        postEvent(TransferEditFavoriteViewEvents.NavigateToDestinationAmount(accountValue))
    }

    private fun handleRemoveAccount(accountValue: TransactionClientBankEntity) {
        viewModelScope.launch {
            accountRemoveFavoriteUseCase.invoke(
                AccountRemoveFavoriteParam(item = accountValue)
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

                    is Result.Success -> {
                        setState {
                            it.copy(
                                loading = false,
                                accountsFavorite = result.data,
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