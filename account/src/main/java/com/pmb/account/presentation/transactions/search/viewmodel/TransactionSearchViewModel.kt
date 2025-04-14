package com.pmb.account.presentation.transactions.search.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.pmb.account.presentation.component.TransactionModel
import com.pmb.account.usecase.deposits.TransactionSearchUseCase
import com.pmb.core.platform.BaseViewAction
import com.pmb.core.platform.BaseViewEvent
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.BaseViewState
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
}

sealed interface TransactionSearchViewActions : BaseViewAction {
    data class SearchTransactions(val value: String) : TransactionSearchViewActions
    object NavigateBack : TransactionSearchViewActions

}

data class TransactionSearchViewState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val query: String = "",
    val transactionList: List<TransactionModel> = emptyList(),
) : BaseViewState