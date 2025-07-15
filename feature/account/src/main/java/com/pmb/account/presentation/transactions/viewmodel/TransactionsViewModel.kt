package com.pmb.account.presentation.transactions.viewmodel

import androidx.lifecycle.viewModelScope
import com.pmb.account.presentation.transactions.filterScreen.viewmodel.entity.TransactionFilter
import com.pmb.core.platform.BaseViewAction
import com.pmb.core.platform.BaseViewEvent
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.BaseViewState
import com.pmb.core.platform.Result
import com.pmb.domain.model.DepositModel
import com.pmb.domain.model.TransactionModel
import com.pmb.domain.model.TransactionRequest
import com.pmb.domain.model.TransactionType
import com.pmb.domain.usecae.deposit.GetUserDepositListUseCase
import com.pmb.domain.usecae.transactions.TransactionsByCountUsaCase
import com.pmb.domain.usecae.transactions.TransactionsByDateUsaCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import java.net.URLEncoder
import javax.inject.Inject

@HiltViewModel
class TransactionsViewModel @Inject constructor(
    initialState: TransactionsViewState,
    private val getDepositsUseCase: GetUserDepositListUseCase,
    private val getTransactionsUseCase: TransactionsByCountUsaCase,
    private val getTransactionsByDateUsaCase: TransactionsByDateUsaCase
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

                // TODO: this is temporary.
                val json = Json { ignoreUnknownKeys = true }
                val transactionJson = json.encodeToString(action.transactionId)
                val e = URLEncoder.encode(transactionJson, "UTF-8")

                postEvent(TransactionsViewEvents.NavigateToTransactionInfoScreen(e))
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
                    selectDeposit(action.model)
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
                            postEvent(TransactionsViewEvents.ShowError(result.message))
                        }

                        Result.Loading -> {
                            setState { it.copy(isLoading = true) }
                        }

                        is Result.Success<*> -> {

                            val listOfDeposits: List<DepositModel> =
                                (result.data as List<DepositModel>)
                            val selectedDeposit = listOfDeposits.firstOrNull()

                            setState {
                                it.copy(
                                    deposits = listOfDeposits,
                                    selectedDeposit = selectedDeposit,
                                    isLoading = false
                                )
                            }

                            viewState.value.selectedDeposit?.let {
                                loadTransactions(
                                    it
                                )
                                loadTransactionByDate(
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

            getTransactionsUseCase.invoke(
                TransactionRequest(
                    extAccNo = deposit.depositNumber.toLong(),
                    count = 10,
                    categoryCode = deposit.categoryCode
                )
            ).collect { result ->
                when (result) {
                    is Result.Error -> {
                        setState {
                            it.copy(
                                errorMessage = result.message,
                                isLoading = false,
                                allTransactions = listOf()
                            )
                        }
                        postEvent(TransactionsViewEvents.ShowError(result.message))

                    }

                    Result.Loading -> {
                        setState { it.copy(isLoading = true) }
                    }

                    is Result.Success<*> -> {
                        val allTransactions: List<TransactionModel> =
                            result.data as List<TransactionModel>

                        setState {
                            it.copy(
                                allTransactions = allTransactions,
                                isLoading = false
                            )
                        }
                    }
                }
            }
        }
    }

    private fun loadTransactionByDate(deposit: DepositModel) {
        viewModelScope.launch {

            getTransactionsByDateUsaCase.invoke(
                TransactionRequest(
                    extAccNo = deposit.depositNumber.toLong(),
                    categoryCode = deposit.categoryCode,
                    fromDate = 14040324,
                    toDate = 14040424
                )
            ).collect { result ->
                when (result) {
                    is Result.Error -> {
                        setState {
                            it.copy(
                                errorMessage = result.message,
                                isLoading = false,
                                sendTransactions = listOf(),
                                receiveTransactions = listOf()
                            )
                        }
                        postEvent(TransactionsViewEvents.ShowError(result.message))

                    }

                    Result.Loading -> {
                        setState { it.copy(isLoading = true) }
                    }

                    is Result.Success<*> -> {
                        val allTransactionsByDate: List<TransactionModel> =
                            result.data as List<TransactionModel>

                        val sendTransactions: List<TransactionModel> =
                            allTransactionsByDate.filter { it.type == TransactionType.TRANSFER }
                        val totalSendTransactions: Double = sendTransactions.sumOf { it.amount }

                        val receiveTransactions: List<TransactionModel> =
                            allTransactionsByDate.filter { it.type == TransactionType.RECEIVE }
                        val totalReceiveTransactions: Double =
                            receiveTransactions.sumOf { it.amount }

                        setState {
                            it.copy(
                                sendTransactions = sendTransactions,
                                totalSendTransactions = totalSendTransactions,
                                receiveTransactions = receiveTransactions,
                                totalReceiveTransactions = totalReceiveTransactions,
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
        postEvent(TransactionsViewEvents.DepositSelectionChanged(deposit.depositNumber))
        loadTransactions(deposit)
        loadTransactionByDate(deposit)
    }
}

sealed interface TransactionsViewEvents : BaseViewEvent {
    object NavigateBack : TransactionsViewEvents
    object NavigateToTransactionSearchScreen : TransactionsViewEvents
    class NavigateToTransactionInfoScreen(val transaction: String) : TransactionsViewEvents
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
    class NavigateToTransactionInfoScreen(val transactionId: TransactionModel) :
        TransactionsViewActions

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