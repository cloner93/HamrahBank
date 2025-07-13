package com.pmb.account.presentation.deposits.viewmodel

import androidx.lifecycle.viewModelScope
import com.pmb.account.presentation.deposits.viewmodel.DepositsViewEvents.NavigateToTransactionDetails
import com.pmb.account.presentation.deposits.viewmodel.DepositsViewEvents.ShowToast
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.Result
import com.pmb.domain.model.DepositModel
import com.pmb.domain.model.TransactionModel
import com.pmb.domain.usecae.deposit.GetUserDepositListUseCase
import com.pmb.domain.usecae.transactions.TransactionsByCountUsaCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DepositsViewModel @Inject constructor(
    initialState: DepositsViewState,
    private val getDepositsUseCase: GetUserDepositListUseCase,
    private val getTransactionsUseCase: TransactionsByCountUsaCase,

    ) : BaseViewModel<DepositsViewActions, DepositsViewState, DepositsViewEvents>(initialState) {

    init {
        loadDeposits()
    }

    override fun handle(action: DepositsViewActions) {
        when (action) {
            is DepositsViewActions.ShowHelp -> {
                postEvent(ShowToast("Help information displayed"))
            }

            is DepositsViewActions.NavigateToBalanceScreen -> {
                postEvent(DepositsViewEvents.NavigateToBalanceScreen)
            }

            is DepositsViewActions.ShowShareBottomSheet -> {
                setState { it.copy(showShareDepositInfoBottomSheet = true) }
            }

            is DepositsViewActions.CloseShareBottomSheet -> {
                setState { it.copy(showShareDepositInfoBottomSheet = false) }
            }

            is DepositsViewActions.NavigateToTransactionScreen -> {
                postEvent(DepositsViewEvents.NavigateToTransactionsList)
            }

            is DepositsViewActions.NavigateToTransactionDetailScreen -> {
                postEvent(NavigateToTransactionDetails(action.transactionId))
            }

            is DepositsViewActions.ShowDepositMoreActionBottomSheet -> {
                setState { it.copy(showMoreBottomSheet = true) }
            }

            is DepositsViewActions.SetAmountVisibility -> {
                setState { it.copy(isAmountVisible = !it.isAmountVisible) }
            }

            is DepositsViewActions.ShowDepositListBottomSheet -> {
                setState { it.copy(showDepositListBottomSheet = true) }
            }

            is DepositsViewActions.CloseDepositListBottomSheet -> {
                setState { it.copy(showDepositListBottomSheet = false) }

                if (action.model != null) {
                    selectDeposit(action.model)
                }
            }

            is DepositsViewActions.CloseDepositMoreActionBottomSheet -> {
                setState { it.copy(showMoreBottomSheet = false) }
            }
        }
    }

    private fun loadDeposits() {
        viewModelScope.launch {

            getDepositsUseCase.invoke(Unit)
                .collect { result ->
                    when (result) {
                        is Result.Error -> {
                            setState {
                                it.copy(
                                    errorMessage = result.message,
                                    isLoading = false
                                )
                            }
                            postEvent(DepositsViewEvents.ShowError(result.message))
                        }

                        Result.Loading -> {
                            setState { it.copy(isLoading = true) }
                        }

                        is Result.Success<*> -> {
                            val listOfDeposits: List<DepositModel> =
                                (result.data as List<DepositModel>).mapIndexed { index, deposit ->
                                    deposit.copy(isSelected = index == 0)
                                }
                            val selectedDeposit = listOfDeposits.firstOrNull()
                            val balance = listOfDeposits.sumOf { deposit -> deposit.amount }

                            setState {
                                it.copy(
                                    deposits = listOfDeposits,
                                    selectedDeposit = selectedDeposit,
                                    totalBalance = balance,
                                    isLoading = false
                                )
                            }

                            viewState.value.selectedDeposit?.let {
                                loadTransactions(
                                    it
                                )
                            }
                        }
                    }
                }
        }
    }

    private fun loadTransactions(deposit: DepositModel) {
        viewModelScope.launch {
            getTransactionsUseCase.invoke(deposit).collect { result ->
                when (result) {
                    is Result.Error -> {
                        setState {
                            it.copy(
                                errorMessage = result.message,
                                isLoading = false
                            )
                        }
                        postEvent(DepositsViewEvents.ShowError(result.message))

                    }//

                    Result.Loading -> {
                        setState { it.copy(isLoading = true) }
                    }

                    is Result.Success<*> -> {
                        val listOfTransactions: List<TransactionModel> =
                            result.data as List<TransactionModel>

                        setState {
                            it.copy(
                                transactions = listOfTransactions,
                                isLoading = false
                            )
                        }
                    }
                }
            }
        }
    }

    fun selectDeposit(deposit: DepositModel) {
        val selectedDeposit =
            viewState.value.deposits.find { it.depositNumber == deposit.depositNumber }

        setState {
            it.copy(
                deposits = viewState.value.deposits
                    .map {
                        if (it.depositNumber == deposit.depositNumber) {
                            it.copy(isSelected = true)
                        } else {
                            it.copy(isSelected = false)
                        }
                    }

            )
        }

        setState { it.copy(selectedDeposit = selectedDeposit) }
        postEvent(DepositsViewEvents.DepositSelectionChanged(deposit.depositNumber))
        loadTransactions(deposit)
    }

    /*  fun hideAllBottomSheets() {
          setState {
              it.copy(
                  showShareDepositInfoBottomSheet = false,
                  showMoreBottomSheet = false,
                  showDepositListBottomSheet = false
              )
          }
      }*/

    /* fun refresh() {
         viewModelScope.launch {
             setState { it.copy(isRefreshing = true) }
             loadDeposits()
             setState { it.copy(isRefreshing = false) }
             postEvent(DepositsViewEvents.RefreshCompleted)
         }
     }*/
}