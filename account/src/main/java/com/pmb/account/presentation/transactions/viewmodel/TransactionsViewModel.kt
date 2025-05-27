package com.pmb.account.presentation.transactions.viewmodel

import androidx.lifecycle.viewModelScope
import com.pmb.account.presentation.component.DepositModel
import com.pmb.account.presentation.component.TransactionModel
import com.pmb.account.presentation.component.TransactionType
import com.pmb.account.presentation.transactions.filterScreen.viewmodel.entity.TransactionFilter
import com.pmb.account.usecase.deposits.GetDepositsUseCase
import com.pmb.account.usecase.deposits.GetTransactionsUseCase
import com.pmb.core.platform.BaseViewAction
import com.pmb.core.platform.BaseViewEvent
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.BaseViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionsViewModel @Inject constructor(
    initialState: TransactionsViewState,
    private val getDepositsUseCase: GetDepositsUseCase,
    private val getTransactionsUseCase: GetTransactionsUseCase
) : BaseViewModel<TransactionsViewActions, TransactionsViewState, TransactionsViewEvents>(
    initialState
) {

    init {
        loadDeposits()
    }

    override fun handle(action: TransactionsViewActions) {
        when (action) {
            TransactionsViewActions.NavigateBack -> {
                postEvent(TransactionsViewEvents.NavigateBack)
            }

            TransactionsViewActions.NavigateToDepositStatementScreen -> {
                postEvent(TransactionsViewEvents.NavigateToDepositStatementScreen)
            }

            TransactionsViewActions.NavigateToTransactionFilterScreen -> {
                postEvent(TransactionsViewEvents.NavigateToTransactionFilterScreen)
            }

            is TransactionsViewActions.NavigateToTransactionInfoScreen -> {
                postEvent(TransactionsViewEvents.NavigateToTransactionInfoScreen(action.transactionId))
            }

            TransactionsViewActions.NavigateToTransactionSearchScreen -> {
                postEvent(TransactionsViewEvents.NavigateToTransactionSearchScreen)
            }

            is TransactionsViewActions.RemoveFilterFromList -> {
                setState {
                    it.copy(
                        transactionFilter = action.item
                    )
                }
            }

            TransactionsViewActions.ShowDepositListBottomSheet -> {
                setState {
                    it.copy(showDepositListBottomSheet = true)
                }
            }

            is TransactionsViewActions.CloseDepositListBottomSheet -> {
                setState { it.copy(showDepositListBottomSheet = false) }

                if (action.model != null) {
                    selectDeposit(action.model.depositNumber)
                }
            }

            is TransactionsViewActions.UpdateFilterList -> {
                setState {
                    it.copy(transactionFilter = action.data)
                }
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

                setState {
                    it.copy(
                        deposits = listOfDeposits,
                        selectedDeposit = selectedDeposit,
                        isLoading = false
                    )
                }

                loadTransactions(viewState.value.selectedDeposit?.depositNumber)
            } catch (e: Exception) {
                setState {
                    it.copy(
                        errorMessage = e.message ?: "Failed to load deposits",
                        isLoading = false
                    )
                }
                postEvent(TransactionsViewEvents.ShowError(e.message ?: "Failed to load deposits"))
            }
        }
    }

    private fun loadTransactions(depositId: String?) {
        if (depositId == null) return

        viewModelScope.launch {
            setState { it.copy(isLoading = true) }

            try {
                val allTransactions: List<TransactionModel> = getTransactionsUseCase(depositId)

                val sendTransactions: List<TransactionModel> =
                    allTransactions.filter { it.type == TransactionType.TRANSFER }
                val totalSendTransactions: Double = sendTransactions.sumOf { it.amount }

                val receiveTransactions: List<TransactionModel> =
                    allTransactions.filter { it.type == TransactionType.RECEIVE }
                val totalReceiveTransactions: Double = receiveTransactions.sumOf { it.amount }

                setState {
                    it.copy(
                        allTransactions = allTransactions,
                        sendTransactions = sendTransactions,
                        totalSendTransactions = totalSendTransactions,
                        receiveTransactions = receiveTransactions,
                        totalReceiveTransactions = totalReceiveTransactions,
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
                postEvent(
                    TransactionsViewEvents.ShowError(
                        e.message ?: "Failed to load transactions"
                    )
                )
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
        postEvent(TransactionsViewEvents.DepositSelectionChanged(depositNumber))
        loadTransactions(depositNumber)
    }
}

sealed interface TransactionsViewEvents : BaseViewEvent {
    object NavigateBack : TransactionsViewEvents
    object NavigateToTransactionSearchScreen : TransactionsViewEvents
    class NavigateToTransactionInfoScreen(val transactionId: String) : TransactionsViewEvents
    object NavigateToTransactionFilterScreen : TransactionsViewEvents
    object NavigateToDepositStatementScreen : TransactionsViewEvents
    data class ShowError(val message: String) : TransactionsViewEvents
    data class ShowToast(val message: String) : TransactionsViewEvents
    data class DepositSelectionChanged(val depositId: String) : TransactionsViewEvents

}

sealed interface TransactionsViewActions : BaseViewAction {
    object ShowDepositListBottomSheet : TransactionsViewActions
    object NavigateToTransactionSearchScreen : TransactionsViewActions
    object NavigateBack : TransactionsViewActions
    object NavigateToTransactionFilterScreen : TransactionsViewActions
    object NavigateToDepositStatementScreen : TransactionsViewActions
    class NavigateToTransactionInfoScreen(val transactionId: String) : TransactionsViewActions
    class RemoveFilterFromList(val item: TransactionFilter) : TransactionsViewActions
    class UpdateFilterList(val data: TransactionFilter) : TransactionsViewActions
    class CloseDepositListBottomSheet(val model: DepositModel?) : TransactionsViewActions
}

data class TransactionsViewState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val transactionFilter: TransactionFilter? = null,
    val allTransactions: List<TransactionModel> = emptyList(),
    val sendTransactions: List<TransactionModel> = emptyList(),
    val totalSendTransactions: Double = 0.0,
    val receiveTransactions: List<TransactionModel> = emptyList(),
    val totalReceiveTransactions: Double = 0.0,
    val deposits: List<DepositModel> = emptyList(),
    val selectedDeposit: DepositModel? = null,
    val showDepositListBottomSheet: Boolean = false,
) : BaseViewState
