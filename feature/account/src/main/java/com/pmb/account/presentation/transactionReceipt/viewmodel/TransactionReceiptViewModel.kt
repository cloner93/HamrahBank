package com.pmb.account.presentation.transactionReceipt.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.pmb.core.platform.BaseViewAction
import com.pmb.core.platform.BaseViewEvent
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.BaseViewState
import com.pmb.core.utils.toCurrency
import com.pmb.domain.model.TransactionModel
import com.pmb.receipt.model.RowData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import java.net.URLDecoder
import javax.inject.Inject

@HiltViewModel
class TransactionReceiptViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    initialState: TransactionReceiptViewState,
) : BaseViewModel<TransactionReceiptViewActions, TransactionReceiptViewState, TransactionReceiptViewEvents>(
    initialState
) {
    private val depositId = savedStateHandle.get<String>("depositId")
    private val transactionJson = savedStateHandle.get<String>("transactionJson")

    init {
        loadTransaction()
    }

    private fun loadTransaction() {
        viewModelScope.launch {
            setState { it.copy(isLoading = true, errorMessage = null) }

            try {
                val json = Json { ignoreUnknownKeys = true }
                val jsonString = URLDecoder.decode(transactionJson, "UTF-8")
                val transaction = json.decodeFromString<TransactionModel>(jsonString)

                val rows = transaction.mapToRows()
                setState {
                    it.copy(
                        isLoading = false,
                        transaction = transaction,
                        rows = rows
                    )
                }
            } catch (e: Exception) {
                setState {
                    it.copy(
                        isLoading = false,
                        errorMessage = e.message
                    )
                }
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
                        "از حساب",
                ),
//                RowData.TwoText(
//                    title = "حساب مبدأ",
//                    subtitle =
//                        "۶۰۳۷۶۹۱۹۰۱۲۳۴۵۶۷",
//                ),
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
) : BaseViewState {
    val sharedText: String
        get() {
            val res = StringBuilder()

            res.append("رسید تراکنش بانک ملت")
            res.append("\n")

            if (rows.isEmpty()) "" else rows.forEach {
                val row: StringBuilder = StringBuilder(it.title)

                when (it) {
                    is RowData.Payment -> {
                        row.append(": " + it.amount + " " + it.currency)
                    }

                    is RowData.Simple -> {
                    }

                    is RowData.TwoText -> {
                        row.append(": " + it.subtitle)
                    }
                }
                res.append(row).append("\n")
            }

            return res.toString()
        }
}