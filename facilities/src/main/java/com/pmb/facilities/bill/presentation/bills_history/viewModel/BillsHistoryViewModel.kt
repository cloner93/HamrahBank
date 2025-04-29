package com.pmb.facilities.bill.presentation.bills_history.viewModel

import androidx.lifecycle.viewModelScope
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.Result
import com.pmb.facilities.bill.domain.bills_history.useCase.GetBillsHistoryDataUseCase
import com.pmb.facilities.bill.domain.bills_history.useCase.GetBillsHistoryFilterUseCase
import com.pmb.facilities.charge.domain.charge_history.entity.ChargeHistoryParams
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BillsHistoryViewModel @Inject constructor(
    initialState: BillsHistoryViewState,
    private val getBillsHistoryDataUseCase: GetBillsHistoryDataUseCase,
    private val getBillsHistoryFilterUseCase: GetBillsHistoryFilterUseCase
) : BaseViewModel<BillsHistoryViewActions, BillsHistoryViewState, BillsHistoryViewEvents>(
    initialState
) {
    override fun handle(action: BillsHistoryViewActions) {
        when (action) {
            is BillsHistoryViewActions.GetBillsHistory -> {
                handleGetBillsHistoryData(action)
            }

            is BillsHistoryViewActions.ClearAlertModelState -> {
                setState {
                    it.copy(
                        isLoading = false
                    )
                }
            }

            is BillsHistoryViewActions.GetFilterData -> {
                handleGetFilterData()
            }

            is BillsHistoryViewActions.SetSelectedFilter -> {
                handleSelectedFilter(action)
            }
        }
    }

    private fun handleGetBillsHistoryData(history: BillsHistoryViewActions.GetBillsHistory) {
        viewModelScope.launch {
            getBillsHistoryDataUseCase.invoke(
                ChargeHistoryParams(filter = history.filter)
            ).collectLatest { result ->
                when (result) {
                    is Result.Error -> {
                        setState {
                            it.copy(
                                isLoading = false,
                                alertModelState = AlertModelState.Dialog(
                                    title = "Get data  failed",
                                    description = "failed to fetch data because ${result.message}",
                                    positiveButtonTitle = "okay",
                                    onPositiveClick = {
                                        setState { state -> state.copy(alertModelState = null) }
                                    }
                                )
                            )
                        }
                    }

                    is Result.Success -> {
                        setState {
                            it.copy(
                                isLoading = false,
                                dataList = result.data.data
                            )
                        }
                    }

                    is Result.Loading -> {
                        setState {
                            it.copy(isLoading = true)
                        }
                    }
                }
            }
        }
    }

    private fun handleGetFilterData() {
        viewModelScope.launch {
            getBillsHistoryFilterUseCase.invoke(Unit).collectLatest { result ->
                when (result) {
                    is Result.Error -> {
                        setState {
                            it.copy(
                                isLoading = false,
                                alertModelState = AlertModelState.Dialog(
                                    title = "Get data  failed",
                                    description = "failed to fetch data because ${result.message}",
                                    positiveButtonTitle = "okay",
                                    onPositiveClick = {
                                        setState { state -> state.copy(alertModelState = null) }
                                    }
                                )
                            )
                        }
                    }

                    is Result.Success -> {
                        setState {
                            it.copy(
                                isLoading = false,
                                filter = result.data.chargeFilterList.findLast { it.isSelected.value }?.filterName,
                                filterItemList = result.data.chargeFilterList
                            )
                        }
                        postEvent(
                            BillsHistoryViewEvents.SelectedFilterIsSuccess(
                                viewState.value.filter ?: ""
                            )
                        )
                    }

                    is Result.Loading -> {
                        setState {
                            it.copy(isLoading = true)
                        }
                    }
                }
            }
        }
    }

    private fun handleSelectedFilter(filter: BillsHistoryViewActions.SetSelectedFilter) {
        viewModelScope.launch {
            if (filter.filter != viewState.value.filter) {
                viewState.value.filterItemList?.findLast { !it.isSelected.value && it.filterName == filter.filter }
                    ?.apply {
                        isSelected.value = true
                    }
                viewState.value.filterItemList?.findLast { it.filterName == viewState.value.filter }
                    ?.apply {
                        isSelected.value = false
                    }
                setState {
                    it.copy(
                        filter = filter.filter
                    )
                }
                postEvent(BillsHistoryViewEvents.SelectedFilterIsSuccess(filter.filter))
            }
        }
    }

    init {
        handle(BillsHistoryViewActions.GetFilterData)
    }
}