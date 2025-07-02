package com.pmb.account.presentation.balance.viewmodel

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewModelScope
import com.pmb.account.presentation.balance.DepositsChartModel
import com.pmb.account.utils.mapToDepositsChartModel
import com.pmb.core.platform.BaseViewAction
import com.pmb.core.platform.BaseViewEvent
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.BaseViewState
import com.pmb.core.platform.Result
import com.pmb.domain.model.DepositModel
import com.pmb.domain.usecae.deposit.GetUserDepositListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BalanceViewModel @Inject constructor(
    initialState: BalanceViewState,
    private val getDepositsUseCase: GetUserDepositListUseCase,
) : BaseViewModel<BalanceViewActions, BalanceViewState, BalanceViewEvents>(initialState) {
    override fun handle(action: BalanceViewActions) {
        when (action) {

            else -> {}
        }
    }

    init {
        loadDeposits()
    }

    private fun loadDeposits() {
        viewModelScope.launch {

            getDepositsUseCase.invoke(Unit)
                .collect { result ->
                    when (result) {
                        is Result.Error -> {
                            setState {
                                it.copy(
                                    errorMessage = result.message,
                                    isLoading = false
                                )
                            }
                            postEvent(BalanceViewEvents.ShowError(result.message))
                        }

                        Result.Loading -> {
                            setState { it.copy(isLoading = true) }
                        }

                        is Result.Success<*> -> {
                            val listOfDeposits = (result.data as List<DepositModel>)
                                .mapToDepositsChartModel()
                            val balance = listOfDeposits.sumOf { deposit -> deposit.amount }

                            setState {
                                it.copy(
                                    deposits = listOfDeposits,
                                    totalBalance = balance,
                                    isLoading = false
                                )
                            }
                        }
                    }
                }
        }
    }

}

fun generateColorByIndex(index: Int): Color {
    val hue = (index * 137.508f) % 360
    return Color.hsv(hue, 0.7f, 0.9f)
}

sealed interface BalanceViewEvents : BaseViewEvent {
    data class ShowError(val message: String) : BalanceViewEvents
}

sealed interface BalanceViewActions : BaseViewAction
data class BalanceViewState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val deposits: List<DepositsChartModel> = listOf(),
    val totalBalance: Double = 0.0,
) : BaseViewState
