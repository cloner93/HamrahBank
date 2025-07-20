package com.pmb.account.presentation.transactions.viewmodel

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.pmb.account.presentation.transactions.filterScreen.TransactionType
import com.pmb.account.presentation.transactions.filterScreen.viewmodel.entity.TransactionFilter
import com.pmb.account.presentation.transactions.viewmodel.TransactionsViewEvents.NavigateToTransactionInfoScreen
import com.pmb.calender.currentMonthPair
import com.pmb.calender.toLong
import com.pmb.core.platform.BaseViewAction
import com.pmb.core.platform.BaseViewEvent
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.BaseViewState
import com.pmb.core.platform.Result
import com.pmb.domain.model.DepositModel
import com.pmb.domain.model.TransactionModel
import com.pmb.domain.model.TransactionRequest
import com.pmb.domain.model.transaztion.Summarize
import com.pmb.domain.usecae.deposit.GetUserDepositListUseCase
import com.pmb.domain.usecae.transactions.GetSummarizeUseCase
import com.pmb.domain.usecae.transactions.TransactionsByCountPagingUsaCase
import com.pmb.domain.usecae.transactions.TransactionsByDatePagingUsaCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.persiancalendar.calendar.PersianDate
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import java.net.URLEncoder
import javax.inject.Inject

@HiltViewModel
class TransactionsViewModel @Inject constructor(
    initialState: TransactionsViewState,
    private val getDepositsUseCase: GetUserDepositListUseCase,
    private val getTransactionsByCountPaging: TransactionsByCountPagingUsaCase,
    private val getTransactionsByDatePaging: TransactionsByDatePagingUsaCase,
    private val getSummarizeUseCase: GetSummarizeUseCase
) : BaseViewModel<TransactionsViewActions, TransactionsViewState, TransactionsViewEvents>(
    initialState
) {

    // we cont handle paging flow inside of ViewState.
    private val _transactionFlow = MutableStateFlow<
            PagingData<TransactionModel>>(PagingData.empty())
    val transactionFlow: StateFlow<PagingData<TransactionModel>> = _transactionFlow

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

                postEvent(NavigateToTransactionInfoScreen(e))
            }

            TransactionsViewActions.NavigateToTransactionSearchScreen -> {
                postEvent(
                    TransactionsViewEvents.NavigateToTransactionSearchScreen(
                        viewState.value.selectedDeposit
                    )
                )
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
                setState {
                    it.copy(
                        showDepositListBottomSheet = false,
                        transactionFilter = null
                    )
                }

                if (action.model != null) {
                    selectDeposit(action.model)
                }
            }

            is TransactionsViewActions.UpdateFilterList -> {
                setState {
                    it.copy(transactionFilter = action.data)
                }

                loadTransactionsByFilter(
                    viewState.value.selectedDeposit!!,
                    action.data
                )
            }

            is TransactionsViewActions.SelectReceiveMonth -> {
                setState {
                    it.copy(currentReceiveMonth = action.dates)
                }

                viewState.value.selectedDeposit?.let {
                    loadSummarizeTransaction(
                        it,
                        viewState.value.currentReceiveMonth,
                        2
                    )
                }
            }

            is TransactionsViewActions.SelectSendMonth -> {
                setState {
                    it.copy(
                        currentSendMonth = action.dates,
                        sendTransactions = emptyList(),
                        totalSendTransactions = 0.0
                    )
                }

                viewState.value.selectedDeposit?.let {
                    loadSummarizeTransaction(
                        it,
                        viewState.value.currentSendMonth,
                        1
                    )
                }

            }

            is TransactionsViewActions.SelectTab -> {
                setState { it.copy(selectedTab = action.id) }
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
                                loadTransactionsByCount(
                                    it
                                )
                            }
                        }
                    }
                }
        }
    }

    private fun loadTransactionsByCount(deposit: DepositModel) {
        getTransactionsByCountPaging(
            TransactionRequest(
                extAccNo = deposit.depositNumber.toLong(),
                count = 10,
                categoryCode = deposit.categoryCode
            )
        ).cachedIn(viewModelScope)
            .onEach { pagingData ->
                _transactionFlow.value = pagingData
            }.launchIn(viewModelScope)
    }

    private fun loadTransactionsByFilter(deposit: DepositModel, data: TransactionFilter) {

        val request = TransactionRequest(
            extAccNo = deposit.depositNumber.toLong(),
            count = 10,
            categoryCode = deposit.categoryCode,
            fromDate = data.fromDate?.toLong() ?: 0,
            toDate = data.toDate?.toLong() ?: 0,
            transType = when (data.transactionType) {
                TransactionType.ALL -> 0
                TransactionType.SEND -> 1
                TransactionType.RECEIVE -> 2
                null -> 0
            }.toLong()
        )

        getTransactionsByDatePaging(request)
            .cachedIn(viewModelScope)
            .onEach { pagingData ->
                _transactionFlow.value = pagingData
            }
            .launchIn(viewModelScope)
    }

    private fun loadSummarizeTransaction(
        deposit: DepositModel,
        month: Pair<PersianDate, PersianDate>,
        type: Int
    ) {
        viewModelScope.launch {
            val req = TransactionRequest(
                fromDate = month.first.toLong(),
                toDate = month.second.toLong(),
                extAccNo = deposit.depositNumber.toLong(),
                transType = type.toLong()
            )
            getSummarizeUseCase.invoke(req).collect { result ->
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
                        val list: List<Summarize> = (result.data as List<Summarize>)
                        var total: Long = 0
                        list.forEach {
                            if (it.totalAmount.isNotBlank())
                                total += it.totalAmount.toLong()
                        }

                        when (type) {
                            1 -> {
                                setState {
                                    it.copy(
                                        sendTransactions = list,
                                        totalSendTransactions = total.toDouble()
                                    )
                                }
                            }

                            2 -> {
                                setState {
                                    it.copy(
                                        receiveTransactions = list,
                                        totalReceiveTransactions = total.toDouble()
                                    )
                                }
                            }
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
        loadTransactionsByCount(deposit)
    }
}

sealed interface TransactionsViewEvents : BaseViewEvent {
    object NavigateBack : TransactionsViewEvents
    class NavigateToTransactionSearchScreen(
        val deposit: DepositModel?
    ) : TransactionsViewEvents

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
    class SelectTab(val id: Int) : TransactionsViewActions
    class SelectSendMonth(val dates: Pair<PersianDate, PersianDate>) : TransactionsViewActions
    class SelectReceiveMonth(val dates: Pair<PersianDate, PersianDate>) : TransactionsViewActions
    class UpdateFilterList(val data: TransactionFilter) : TransactionsViewActions
    class CloseDepositListBottomSheet(val model: DepositModel?) : TransactionsViewActions
}

data class TransactionsViewState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val selectedTab: Int = 0,
    val transactionFilter: TransactionFilter? = null,
    val allTransactions: List<TransactionModel> = emptyList(),

    val sendTransactions: List<Summarize>? = null,
    val receiveTransactions: List<Summarize>? = null,

    val currentSendMonth: Pair<PersianDate, PersianDate> = currentMonthPair(), // 14040401 14040431
    val currentReceiveMonth: Pair<PersianDate, PersianDate> = currentMonthPair(), // 14040401 14040431

    val totalSendTransactions: Double = 0.0,
    val totalReceiveTransactions: Double = 0.0,
    val deposits: List<DepositModel> = emptyList(),
    val selectedDeposit: DepositModel? = null,
    val showDepositListBottomSheet: Boolean = false,
) : BaseViewState