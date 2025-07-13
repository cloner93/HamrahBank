package com.pmb.account.presentation.transactions.search.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.pmb.account.usecase.deposits.TransactionSearchUseCase
import com.pmb.core.platform.BaseViewAction
import com.pmb.core.platform.BaseViewEvent
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.BaseViewState
import com.pmb.domain.model.TransactionModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionSearchViewModel @Inject constructor(
    initialState: TransactionSearchViewState,
    savedStateHandle: SavedStateHandle,
    private val transactionSearchUseCase: TransactionSearchUseCase,
) : BaseViewModel<TransactionSearchViewActions, TransactionSearchViewState, TransactionSearchViewEvents>(
    initialState
) {
    private val depositId = savedStateHandle.get<String>("depositId")

    init {
        search()
        setState {
            it.copy(
                depositId = depositId
            )
        }
    }

    override fun handle(action: TransactionSearchViewActions) {
        when (action) {
            is TransactionSearchViewActions.SearchTransactions -> {
                setState {
                    it.copy(
                        query = action.value
                    )
                }

                search()
            }

            TransactionSearchViewActions.NavigateBack -> {
                postEvent(TransactionSearchViewEvents.NavigateBack)
            }

            is TransactionSearchViewActions.NavigateToTransactionInfoScreen -> {
                postEvent(
                    TransactionSearchViewEvents.NavigateToTransactionInfoScreen(
                        action.depositId,
                        action.transactionId
                    )
                )
            }
        }
    }

    private fun search() {
        val query = viewState.value.query
        viewModelScope.launch {
            setState { it.copy(isLoading = true, errorMessage = null) }

            try {
                val result = transactionSearchUseCase(depositId!!, query)
                setState {
                    it.copy(transactionList = result, isLoading = false)
                }
            } catch (e: Exception) {
                setState {
                    it.copy(isLoading = false, errorMessage = e.message)
                }
            }
        }
    }
}

sealed interface TransactionSearchViewEvents : BaseViewEvent {
    object NavigateBack : TransactionSearchViewEvents
    class NavigateToTransactionInfoScreen(val depositId: String, val transactionId: String) :
        TransactionSearchViewEvents
}

sealed interface TransactionSearchViewActions : BaseViewAction {
    data class SearchTransactions(val value: String) : TransactionSearchViewActions
    object NavigateBack : TransactionSearchViewActions
    class NavigateToTransactionInfoScreen(val depositId: String, val transactionId: String) :
        TransactionSearchViewActions

}

data class TransactionSearchViewState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val depositId: String? = null,
    val query: String = "",
    val transactionList: List<TransactionModel> = emptyList(),
) : BaseViewState