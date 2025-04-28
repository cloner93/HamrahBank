package com.pmb.facilities.charge.presentation.charge_history.viewModel

import androidx.lifecycle.viewModelScope
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.Result
import com.pmb.facilities.charge.domain.charge_history.entity.ChargeHistoryParams
import com.pmb.facilities.charge.domain.charge_history.useCase.GetChargeHistoryDataUseCase
import com.pmb.facilities.charge.domain.charge_history.useCase.GetChargeHistoryFilterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChargeHistoryViewModel @Inject constructor(
    initialState: ChargeHistoryViewState,
    private val getChargeHistoryFilterUseCase: GetChargeHistoryFilterUseCase,
    private val getChargeHistoryDataUseCase: GetChargeHistoryDataUseCase
) : BaseViewModel<ChargeHistoryViewActions, ChargeHistoryViewState, ChargeHistoryViewEvents>(
    initialState
) {
    override fun handle(action: ChargeHistoryViewActions) {
        when (action) {
            is ChargeHistoryViewActions.GetChargeHistory -> {
                handleGetChargeHistoryData(action)
            }

            is ChargeHistoryViewActions.ClearAlertModelState -> {
                setState {
                    it.copy(
                        isLoading = false
                    )
                }
            }

            is ChargeHistoryViewActions.GetFilterData -> {
                handleGetFilterData()
            }

            is ChargeHistoryViewActions.SetSelectedFilter -> {
                handleSelectedFilter(action)
            }
        }
    }

    private fun handleGetChargeHistoryData(history: ChargeHistoryViewActions.GetChargeHistory) {
        viewModelScope.launch {
            getChargeHistoryDataUseCase.invoke(
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
            getChargeHistoryFilterUseCase.invoke(Unit).collectLatest { result ->
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
                        postEvent(ChargeHistoryViewEvents.SelectedFilterIsSuccess(viewState.value.filter ?:""))
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

    private fun handleSelectedFilter(filter: ChargeHistoryViewActions.SetSelectedFilter) {
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
                postEvent(ChargeHistoryViewEvents.SelectedFilterIsSuccess(filter.filter))
            }
        }
    }

    init {
        handle(ChargeHistoryViewActions.GetFilterData)
    }
}