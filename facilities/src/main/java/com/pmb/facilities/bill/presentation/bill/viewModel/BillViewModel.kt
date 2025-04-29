package com.pmb.facilities.bill.presentation.bill.viewModel

import androidx.lifecycle.viewModelScope
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.Result
import com.pmb.facilities.bill.domain.bill.entity.BillType
import com.pmb.facilities.bill.domain.bill.entity.BillsType
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
    private val billsType = listOf<BillType>(
        BillType(
            id = 0,
            type = BillsType.OTHER,
            title = "قبوض شرکت های خدماتی"
        ),
        BillType(
            id = 1,
            type = BillsType.TELECOMMUNICATION_BILL,
            title = "قبوض تلفن ثابت و همراه"
        ),
    )

    fun getBillTypeData() = billsType
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

            is BillViewActions.SetBottomSheetVisibility -> {
                handleSetBottomSheetVisibility()
            }

            is BillViewActions.SetBillTypeData -> {
                handleSetBillType(action)
            }
        }
    }

    private fun handleSetBillType(data: BillViewActions.SetBillTypeData) {
        viewModelScope.launch {
            setState {
                it.copy(
                    billType = data.billType
                )
            }
            postEvent(BillViewEvents.SetBillType)
        }
    }

    private fun handleSetBottomSheetVisibility() {
        viewModelScope.launch {
            setState {
                it.copy(
                    isBottomSheetVisibility = !it.isBottomSheetVisibility
                )
            }
        }
    }

    private fun handleGetBillsData() {
        viewModelScope.launch {
            getBillsDataUseCase.invoke(Unit).collectLatest { result ->
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