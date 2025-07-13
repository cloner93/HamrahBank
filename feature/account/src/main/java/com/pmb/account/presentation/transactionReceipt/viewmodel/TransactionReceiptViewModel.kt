package com.pmb.account.presentation.transactionReceipt.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.pmb.account.usecase.deposits.GetTransactionByIdUseCase
import com.pmb.core.platform.BaseViewAction
import com.pmb.core.platform.BaseViewEvent
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.BaseViewState
import com.pmb.core.utils.toCurrency
import com.pmb.domain.model.TransactionModel
import com.pmb.receipt.model.RowData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionReceiptViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    initialState: TransactionReceiptViewState,
    private val transactionsByIdUseCase: GetTransactionByIdUseCase,
) : BaseViewModel<TransactionReceiptViewActions, TransactionReceiptViewState, TransactionReceiptViewEvents>(
    initialState
) {
    private val depositId = savedStateHandle.get<String>("depositId")
    private val transactionId = savedStateHandle.get<String>("transactionId")

    init {
        loadTransaction()
    }

    private fun loadTransaction() {
        viewModelScope.launch {
            setState { it.copy(isLoading = true, errorMessage = null) }

            val result = transactionsByIdUseCase(depositId, transactionId)
            val rows = result.mapToRows()
            setState {
                it.copy(
                    isLoading = false,
                    transaction = result,
                    rows = rows
                )
            }

        }
    }

    private fun TransactionModel?.mapToRows(): List<RowData> {
        var rows = mutableListOf<RowData>()

        if (this != null) {
            rows = listOf(
                RowData.Payment(
                    title = "مبلغ",
                    amount = amount.toCurrency(),
                    currency = currency,
                ),
                RowData.TwoText(
                    title = "زمان",
                    subtitle = date,
                ),
                RowData.TwoText(
                    title = "روش پرداخت",
                    subtitle =
                        "از کارت",
                ),
                RowData.TwoText(
                    title = "کارت مبدأ",
                    subtitle =
                        "۶۰۳۷۶۹۱۹۰۱۲۳۴۵۶۷",
                ),
                RowData.TwoText(
                    title = "شماره پیگیری",
                    subtitle =
                        "۶۹۱۹۰۱۲",
                )
            ).toMutableList()
        }
        return rows
    }

    override fun handle(action: TransactionReceiptViewActions) {

    }


}

sealed interface TransactionReceiptViewEvents : BaseViewEvent {
    object NavigateBack : TransactionReceiptViewEvents
}

sealed interface TransactionReceiptViewActions : BaseViewAction {
    object NavigateBack : TransactionReceiptViewActions

}

data class TransactionReceiptViewState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val transaction: TransactionModel? = null,
    val rows: List<RowData> = emptyList(),
) : BaseViewState