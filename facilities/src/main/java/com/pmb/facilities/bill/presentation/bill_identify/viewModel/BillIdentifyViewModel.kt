package com.pmb.facilities.bill.presentation.bill_identify.viewModel

import androidx.lifecycle.viewModelScope
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.Result
import com.pmb.facilities.bill.domain.bill_id.entity.BillIdParams
import com.pmb.facilities.bill.domain.bill_id.useCase.GetBillDataBasedIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BillIdentifyViewModel @Inject constructor(
    initialState: BillIdentifyViewState,
    private val billDataBasedIdUseCase: GetBillDataBasedIdUseCase
) : BaseViewModel<BillIdentifyViewActions, BillIdentifyViewState, BillIdentifyViewEvents>(
    initialState
) {
    override fun handle(action: BillIdentifyViewActions) {
        when (action) {
            is BillIdentifyViewActions.ClearAlertModelState -> {
                setState {
                    it.copy(
                        isLoading = false
                    )
                }
            }

            is BillIdentifyViewActions.GetBillData -> {
                handleGetBillData(action)
            }

            is BillIdentifyViewActions.SetBottomSheetVisibility -> {
                handleBottomSheetVisibility()
            }
        }
    }

    private fun handleBottomSheetVisibility() {
        setState {
            it.copy(
                bottomSheetVisibility = !it.bottomSheetVisibility
            )
        }
    }

    private fun handleGetBillData(data: BillIdentifyViewActions.GetBillData) {
        viewModelScope.launch {
            billDataBasedIdUseCase.invoke(
                params = BillIdParams(data.billId)
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
                                billIdEntity = result.data
                            )
                        }
                        handle(BillIdentifyViewActions.SetBottomSheetVisibility)
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
}