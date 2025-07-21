package com.pmb.transfer.presentation.transfer_select_favorite.viewmodel

import androidx.lifecycle.viewModelScope
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.Result
import com.pmb.transfer.domain.entity.TransactionClientBankEntity
import com.pmb.transfer.domain.use_case.AccountFavoritesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransferSelectFavoriteViewModel @Inject constructor(
    private val accountFavoritesUseCase: AccountFavoritesUseCase
) :
    BaseViewModel<TransferSelectFavoriteViewActions, TransferSelectFavoriteViewState, TransferSelectFavoriteViewEvents>(
        TransferSelectFavoriteViewState()
    ) {
    init {
        fetchTransferFavorites()
    }

    override fun handle(action: TransferSelectFavoriteViewActions) {
        when (action) {
            TransferSelectFavoriteViewActions.ClearAlert -> setState { it.copy(alertState = null) }
            is TransferSelectFavoriteViewActions.SelectFavorite -> handleSelectFavorite(action.item)
        }
    }

    private fun handleSelectFavorite(item: TransactionClientBankEntity) {
        postEvent(TransferSelectFavoriteViewEvents.TransferDestinationAmount(item))
    }

    private fun fetchTransferFavorites() {
        viewModelScope.launch {
            accountFavoritesUseCase.invoke(Unit).collect { result ->
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