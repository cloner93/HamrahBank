package com.pmb.account.presentation.transactions.filterScreen.viewmodel

import com.pmb.account.presentation.transactions.filterScreen.DateType
import com.pmb.account.presentation.transactions.filterScreen.TransactionType
import com.pmb.account.presentation.transactions.filterScreen.viewmodel.entity.TransactionFilter
import com.pmb.core.platform.BaseViewAction
import com.pmb.core.platform.BaseViewEvent
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.BaseViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TransactionsFilterViewModel @Inject constructor(
    initialState: TransactionsFilterViewState,
) : BaseViewModel<TransactionsFilterViewActions, TransactionsFilterViewState, TransactionsFilterViewEvents>(
    initialState
) {
    override fun handle(action: TransactionsFilterViewActions) {
        when (action) {
            is TransactionsFilterViewActions.ChangeFromPrice -> {
                setState {
                    it.copy(fromPrice = action.fromPrice)
                }
            }

            is TransactionsFilterViewActions.ChangeToPrice -> {
                setState {
                    it.copy(toPrice = action.toPrice)
                }
            }

            is TransactionsFilterViewActions.CloseFromDatePicker -> {
                setState {
                    it.copy(
                        showFromDatePicker = false,
                        fromDate = action.date
                    )
                }
            }

            is TransactionsFilterViewActions.CloseToDatePicker -> {
                setState {
                    it.copy(
                        showToDatePicker = false,
                        toDate = action.date
                    )
                }
            }

            TransactionsFilterViewActions.NavigateBack -> {
                postEvent(TransactionsFilterViewEvents.NavigateBack)
            }

            is TransactionsFilterViewActions.SelectDateType -> {
                setState {
                    it.copy(
                        dateType = action.type
                    )
                }
            }

            is TransactionsFilterViewActions.SelectTransactionType -> {
                setState {
                    it.copy(
                        transactionType = action.type
                    )
                }
            }

            TransactionsFilterViewActions.ShowFromDatePicker -> {
                setState {
                    it.copy(
                        showFromDatePicker = true
                    )
                }
            }

            TransactionsFilterViewActions.ShowToDatePicker -> {
                setState {
                    it.copy(
                        showToDatePicker = true
                    )
                }
            }

            TransactionsFilterViewActions.ApplyFilters -> {
                setState {
                    it.copy(
                        transactionFilter = TransactionFilter(
                            transactionType = it.transactionType,
                            dateType = it.dateType,
                            fromPrice = it.fromPrice,
                            toPrice = it.toPrice,
                            fromDate = it.fromDate,
                        )
                    )
                }
                postEvent(TransactionsFilterViewEvents.NavigateBackWithData)
            }

            TransactionsFilterViewActions.ClearFilters -> {
                setState {
                    it.copy(
                        transactionType = null,
                        fromPrice = null,
                        toPrice = null,
                        dateType = null,
                        transactionFilter = null
                    )
                }
            }
        }
    }
}

sealed interface TransactionsFilterViewEvents : BaseViewEvent {
    object NavigateBack : TransactionsFilterViewEvents
    object NavigateBackWithData : TransactionsFilterViewEvents
}

sealed interface TransactionsFilterViewActions : BaseViewAction {
    object NavigateBack : TransactionsFilterViewActions
    class SelectTransactionType(val type: TransactionType?) : TransactionsFilterViewActions
    class SelectDateType(val type: DateType?) : TransactionsFilterViewActions
    class ChangeFromPrice(val fromPrice: String) : TransactionsFilterViewActions
    class ChangeToPrice(val toPrice: String) : TransactionsFilterViewActions

    object ShowFromDatePicker : TransactionsFilterViewActions
    class CloseFromDatePicker(val date: String?) : TransactionsFilterViewActions

    object ShowToDatePicker : TransactionsFilterViewActions
    class CloseToDatePicker(val date: String?) : TransactionsFilterViewActions

    object ClearFilters : TransactionsFilterViewActions
    object ApplyFilters : TransactionsFilterViewActions
}

data class TransactionsFilterViewState(
    val isLoading: Boolean = false,
    val transactionType: TransactionType? = null,
    val dateType: DateType? = null,
    val fromPrice: String? = "",
    val toPrice: String? = "",
    val showFromDatePicker: Boolean = false,
    val showToDatePicker: Boolean = false,
    val fromDate: String? = null,
    val toDate: String? = null,
    val transactionFilter: TransactionFilter? = null
) : BaseViewState