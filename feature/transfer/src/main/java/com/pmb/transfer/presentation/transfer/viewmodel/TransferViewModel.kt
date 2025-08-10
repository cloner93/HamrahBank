package com.pmb.transfer.presentation.transfer.viewmodel

import androidx.lifecycle.viewModelScope
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.Result
import com.pmb.domain.usecae.favorite.FetchAllTransferFavoriteAccountsUseCase
import com.pmb.transfer.domain.entity.TransactionClientBankEntity
import com.pmb.transfer.domain.entity.toEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransferViewModel @Inject constructor(
    private val fetchAllTransferFavoriteAccountsUseCase: FetchAllTransferFavoriteAccountsUseCase,
) : BaseViewModel<TransferViewActions, TransferViewState, TransferViewEvents>(TransferViewState()) {
    init {
        fetchTransferFavorites()
    }

    override fun handle(action: TransferViewActions) {
        when (action) {
            is TransferViewActions.NavigateToDestinationInput -> handleDestinationInput()
            TransferViewActions.ClearAlert -> setState { it.copy(alertState = null) }
            is TransferViewActions.SelectAccount -> handleSelectAccount(action.item)
            TransferViewActions.CloseGuideBottomSheet -> setState { it.copy(showGuideBottomSheet = false) }
            TransferViewActions.ShowGuideBottomSheet -> setState { it.copy(showGuideBottomSheet = true) }
            TransferViewActions.RefreshFavorites -> fetchTransferFavorites()
        }
    }

    private fun fetchTransferFavorites() {
        viewModelScope.launch {
            fetchAllTransferFavoriteAccountsUseCase.invoke(Unit).collect { result ->
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
                        val favoriteList = result.data.filter { item -> item.type == 0 }
                            .map { item -> item.toEntity() }
                        val accounts = result.data.filter { item -> item.type == 1 }
                            .map { item -> item.toEntity() }
                        setState {
                            it.copy(
                                loading = false,
                                favoriteAccounts = favoriteList,
                                accounts = accounts
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