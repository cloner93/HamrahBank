package com.pmb.account.presentation.transactions.detailedTransactionLIst

import com.pmb.core.platform.BaseViewAction
import com.pmb.core.platform.BaseViewEvent
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.BaseViewState
import com.pmb.domain.model.DepositModel
import com.pmb.domain.model.TransactionModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.serialization.json.Json
import java.net.URLEncoder
import javax.inject.Inject

@HiltViewModel
class DetailedTransactionListViewModel @Inject constructor(
    initialState: DetailedTransactionListViewState,
) : BaseViewModel<DetailedTransactionListViewActions, DetailedTransactionListViewState, DetailedTransactionListViewEvents>(
    initialState
) {
    override fun handle(action: DetailedTransactionListViewActions) {
        when (action) {
            DetailedTransactionListViewActions.NavigateBack -> {}
            is DetailedTransactionListViewActions.OpenTransaction -> {
                val json = Json { ignoreUnknownKeys = true }
                val transactionJson = json.encodeToString(action.transaction)
                val e = URLEncoder.encode(transactionJson, "UTF-8")

                postEvent(DetailedTransactionListViewEvents.NavigateToTransactionDetails(e))
            }
        }
    }
}

sealed interface DetailedTransactionListViewEvents : BaseViewEvent {
    object NavigateBack : DetailedTransactionListViewEvents
    class NavigateToTransactionDetails(val transaction: String) : DetailedTransactionListViewEvents
}

sealed interface DetailedTransactionListViewActions : BaseViewAction {
    object NavigateBack : DetailedTransactionListViewActions
    class OpenTransaction(val transaction: TransactionModel) : DetailedTransactionListViewActions
}

data class DetailedTransactionListViewState(
    val isLoading: Boolean = false,
    val selectedDeposit: DepositModel? = null,
) : BaseViewState