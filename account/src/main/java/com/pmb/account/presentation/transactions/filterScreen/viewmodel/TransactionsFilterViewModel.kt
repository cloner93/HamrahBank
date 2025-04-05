package com.pmb.account.presentation.transactions.filterScreen.viewmodel

import com.pmb.account.presentation.transactions.viewmodel.TransactionsViewActions
import com.pmb.account.presentation.transactions.viewmodel.TransactionsViewEvents
import com.pmb.account.presentation.transactions.viewmodel.TransactionsViewState
import com.pmb.core.platform.BaseViewAction
import com.pmb.core.platform.BaseViewEvent
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.BaseViewState
import javax.inject.Inject

class TransactionsFilterViewModel @Inject constructor(
    initialState: TransactionsViewState,
) : BaseViewModel<TransactionsViewActions, TransactionsViewState, TransactionsViewEvents>(
    initialState
) {
    override fun handle(action: TransactionsViewActions) {
        TODO("Not yet implemented")
    }
}


sealed interface FromViewEvents : BaseViewEvent {
    object NavigateBack : FromViewEvents

}

sealed interface FromViewActions : BaseViewAction

data class FromViewState(
    val isLoading: Boolean = false,
) : BaseViewState