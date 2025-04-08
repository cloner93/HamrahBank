package com.pmb.account.presentation.transactions.statement.viewmodel

import com.pmb.account.presentation.transactions.statement.DateType
import com.pmb.core.platform.BaseViewAction
import com.pmb.core.platform.BaseViewEvent
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.BaseViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DepositStatementViewModel @Inject constructor(
    initialState: DepositStatementViewState,
) : BaseViewModel<DepositStatementViewActions, DepositStatementViewState, DepositStatementViewEvents>(
    initialState
) {
    override fun handle(action: DepositStatementViewActions) {
        when (action) {
            DepositStatementViewActions.Apply -> {
                postEvent(DepositStatementViewEvents.GenerateStatement)
            }

            is DepositStatementViewActions.CloseFromDatePicker -> {
                setState {
                    it.copy(
                        showFromDatePicker = false,
                        fromDate = action.date
                    )
                }
            }

            is DepositStatementViewActions.CloseToDatePicker -> {
                setState {
                    it.copy(
                        showToDatePicker = false,
                        toDate = action.date
                    )
                }
            }

            DepositStatementViewActions.NavigateBack -> {
                postEvent(DepositStatementViewEvents.NavigateBack)
            }

            is DepositStatementViewActions.SelectDateType -> {
                setState {
                    it.copy(
                        dateType = action.type
                    )
                }
            }

            DepositStatementViewActions.ShowFromDatePicker -> {
                setState {
                    it.copy(
                        showFromDatePicker = true
                    )
                }
            }

            DepositStatementViewActions.ShowToDatePicker -> {
                setState {
                    it.copy(
                        showToDatePicker = true
                    )
                }
            }
        }
    }
}

sealed interface DepositStatementViewEvents : BaseViewEvent {
    object NavigateBack : DepositStatementViewEvents
    object GenerateStatement : DepositStatementViewEvents
}

sealed interface DepositStatementViewActions : BaseViewAction {
    object NavigateBack : DepositStatementViewActions
    class SelectDateType(val type: DateType?) : DepositStatementViewActions

    object ShowFromDatePicker : DepositStatementViewActions
    class CloseFromDatePicker(val date: String?) : DepositStatementViewActions

    object ShowToDatePicker : DepositStatementViewActions
    class CloseToDatePicker(val date: String?) : DepositStatementViewActions

    object Apply : DepositStatementViewActions
}

data class DepositStatementViewState(
    val isLoading: Boolean = false,
    val dateType: DateType? = null,
    val showFromDatePicker: Boolean = false,
    val showToDatePicker: Boolean = false,
    val fromDate: String? = null,
    val toDate: String? = null,
) : BaseViewState