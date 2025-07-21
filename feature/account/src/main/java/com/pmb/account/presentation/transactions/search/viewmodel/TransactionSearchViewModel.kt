package com.pmb.account.presentation.transactions.search.viewmodel

import androidx.lifecycle.viewModelScope
import com.pmb.core.platform.BaseViewAction
import com.pmb.core.platform.BaseViewEvent
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.BaseViewState
import com.pmb.domain.model.DepositModel
import com.pmb.domain.model.TransactionModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import java.net.URLEncoder
import javax.inject.Inject

@HiltViewModel
class TransactionSearchViewModel @Inject constructor(
    initialState: TransactionSearchViewState,
) : BaseViewModel<TransactionSearchViewActions, TransactionSearchViewState, TransactionSearchViewEvents>(
    initialState
) {

    init {
        search()
    }

    override fun handle(action: TransactionSearchViewActions) {
        when (action) {
            is TransactionSearchViewActions.SearchTransactions -> {
                setState {
                    it.copy(
                        query = action.value,
                    )
                }

                search()
            }

            TransactionSearchViewActions.NavigateBack -> {
                postEvent(TransactionSearchViewEvents.NavigateBack)
            }

            is TransactionSearchViewActions.NavigateToTransactionInfoScreen -> {
                val json = Json { ignoreUnknownKeys = true }
                val transactionJson = json.encodeToString(action.transaction)
                val e = URLEncoder.encode(transactionJson, "UTF-8")

                viewState.value.deposit?.let {
                    postEvent(
                        TransactionSearchViewEvents.NavigateToTransactionInfoScreen(
                            it,
                            e
                        )
                    )
                }
            }
        }
    }

    private fun search() {
        val query = viewState.value.query
        viewModelScope.launch {
            setState { it.copy(isLoading = true) }

            try {
                val result = transactionSearch(
                    viewState.value.transactionList,
                    query
                )
                setState {
                    it.copy(filteredTransactionList = result)
                }
            } catch (e: Exception) {
                setState {
                    it.copy(errorMessage = e.message)
                }
            }
        }
    }

    fun transactionSearch(
        transactionList: List<TransactionModel>,
        query: String
    ): List<TransactionModel> {
        return transactionList.filter { transaction ->
            transaction.title.contains(query, ignoreCase = true) ||
                    transaction.amount.toString().contains(query, ignoreCase = true) ||
                    transaction.date.contains(query, ignoreCase = true)
        }
    }

    fun setListOfTransaction(transactionList: List<TransactionModel>?, deposit: DepositModel?) {
        setState {
            it.copy(
                transactionList = transactionList ?: emptyList(),
                deposit = deposit
            )
        }
    }
}

sealed interface TransactionSearchViewEvents : BaseViewEvent {
    object NavigateBack : TransactionSearchViewEvents
    class NavigateToTransactionInfoScreen(
        val deposit: DepositModel,
        val transactionJson: String
    ) :
        TransactionSearchViewEvents
}

sealed interface TransactionSearchViewActions : BaseViewAction {
    data class SearchTransactions(val value: String) : TransactionSearchViewActions
    object NavigateBack : TransactionSearchViewActions
    class NavigateToTransactionInfoScreen(
        val transaction: TransactionModel
    ) :
        TransactionSearchViewActions

}

data class TransactionSearchViewState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val query: String = "",
    val transactionList: List<TransactionModel> = emptyList(),
    val filteredTransactionList: List<TransactionModel> = emptyList(),
    val deposit: DepositModel? = null,
) : BaseViewState