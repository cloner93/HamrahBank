package com.pmb.account.presentation.transactions.filterScreen.viewmodel

import com.pmb.account.presentation.transactions.filterScreen.DateType
import com.pmb.account.presentation.transactions.filterScreen.TransactionType
import com.pmb.core.platform.BaseViewAction
import com.pmb.core.platform.BaseViewEvent
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.BaseViewState
import javax.inject.Inject

class TransactionsFilterViewModel @Inject constructor(
    initialState: TransactionsFilterViewState,
) : BaseViewModel<TransactionsFilterViewActions, TransactionsFilterViewState, TransactionsFilterViewEvents>(
    initialState
) {
    override fun handle(action: TransactionsFilterViewActions) {
    }
}

sealed interface TransactionsFilterViewEvents : BaseViewEvent {
    object NavigateBack : TransactionsFilterViewEvents

}

sealed interface TransactionsFilterViewActions : BaseViewAction {
    object NavigateBack : TransactionsFilterViewActions
    class SelectTransactionType(type: TransactionType?) : TransactionsFilterViewActions
    class SelectDateType(type: DateType) : TransactionsFilterViewActions
    class ChangeFromPrice(fromPrice: String) : TransactionsFilterViewActions
    class ChangeToPrice(toPrice: String) : TransactionsFilterViewActions

    object ShowFromDatePicker : TransactionsFilterViewActions
    class CloseFromDatePicker(val date: String?) : TransactionsFilterViewActions

    object ShowToDatePicker : TransactionsFilterViewActions
    class CloseToDatePicker(val date: String?) : TransactionsFilterViewActions
}

data class TransactionsFilterViewState(
    val isLoading: Boolean = false,
    val transactionType: TransactionType? = null,
    val dateType: DateType? = null,
    val fromDate: String = "",
    val toDate: String = "",
    val showFromDatePicker: Boolean = false,
    val showToDatePicker: Boolean = false,
) : BaseViewState