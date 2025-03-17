package com.pmb.account.presentation.deposits.viewmodel

import androidx.lifecycle.viewModelScope
import com.pmb.account.presentation.component.DepositModel
import com.pmb.account.presentation.deposits.viewmodel.DepositsViewEvents.NavigateToTransactionDetails
import com.pmb.account.presentation.deposits.viewmodel.DepositsViewEvents.ShowToast
import com.pmb.account.usecase.deposits.GetDepositsUseCase
import com.pmb.account.usecase.deposits.GetTransactionsUseCase
import com.pmb.core.platform.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DepositsViewModel @Inject constructor(
    initialState: DepositsViewState,
    private val getDepositsUseCase: GetDepositsUseCase,
    private val getTransactionsUseCase: GetTransactionsUseCase,

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
                postEvent(NavigateToTransactionDetails("placeholder_id"))
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
                    selectDeposit(action.model.depositNumber)
                }
            }

            is DepositsViewActions.CloseDepositMoreActionBottomSheet -> {
                setState { it.copy(showMoreBottomSheet = false) }
            }
        }
    }

    private fun loadDeposits() {
        viewModelScope.launch {
            setState { it.copy(isLoading = true) }

            try {
                val listOfDeposits: List<DepositModel> =
                    getDepositsUseCase().mapIndexed { index, deposit ->
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

                // Load transactions for the selected deposit
                loadTransactions(viewState.value.selectedDeposit?.depositNumber)
            } catch (e: Exception) {
                setState {
                    it.copy(
                        errorMessage = e.message ?: "Failed to load deposits",
                        isLoading = false
                    )
                }
                postEvent(DepositsViewEvents.ShowError(e.message ?: "Failed to load deposits"))
            }
        }
    }

    private fun loadTransactions(depositId: String?) {
        if (depositId == null) return

        viewModelScope.launch {
            setState { it.copy(isLoading = true) }

            try {
                val transactions = getTransactionsUseCase(depositId)
                setState {
                    it.copy(
                        transactions = transactions,
                        isLoading = false
                    )
                }
            } catch (e: Exception) {
                setState {
                    it.copy(
                        errorMessage = e.message ?: "Failed to load transactions",
                        isLoading = false
                    )
                }
                postEvent(DepositsViewEvents.ShowError(e.message ?: "Failed to load transactions"))
            }
        }
    }

    fun selectDeposit(depositNumber: String) {
        val selectedDeposit = viewState.value.deposits.find { it.depositNumber == depositNumber }

        setState {
            it.copy(
                deposits = viewState.value.deposits
                    .map {
                        if (it.depositNumber == depositNumber) {
                            it.copy(isSelected = true)
                        } else {
                            it.copy(isSelected = false)
                        }
                    }

            )
        }

        setState { it.copy(selectedDeposit = selectedDeposit) }
        postEvent(DepositsViewEvents.DepositSelectionChanged(depositNumber))
        loadTransactions(depositNumber)
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