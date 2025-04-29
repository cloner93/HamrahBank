package com.pmb.facilities.bill.presentation.bill.viewModel

import androidx.lifecycle.viewModelScope
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.Result
import com.pmb.facilities.bill.domain.bill.useCase.GetBillsDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BillViewModel @Inject constructor(
    initialState: BillViewState,
    private val getBillsDataUseCase: GetBillsDataUseCase
) : BaseViewModel<BillViewActions, BillViewState, BillViewEvents>(initialState) {
    override fun handle(action: BillViewActions) {
        when (action) {
            is BillViewActions.ClearAlertModelState -> {
                setState {
                    it.copy(
                        isLoading = false
                    )
                }
            }
            is BillViewActions.GetBillsData -> {
                handleGetBillsData()
            }
        }
    }

    private fun handleGetBillsData() {
        viewModelScope.launch {
            getBillsDataUseCase.invoke(Unit).collectLatest {result->
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
                                billsData = result.data.data
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
    init {
        handle(BillViewActions.GetBillsData)
    }
}