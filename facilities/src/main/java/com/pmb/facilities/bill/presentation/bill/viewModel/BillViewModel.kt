package com.pmb.facilities.bill.presentation.bill.viewModel

import androidx.lifecycle.viewModelScope
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.Result
import com.pmb.facilities.bill.domain.bill.entity.BillType
import com.pmb.facilities.bill.domain.bill.entity.BillsType
import com.pmb.facilities.bill.domain.bill.useCase.GetBillsDataUseCase
import com.pmb.facilities.bill.domain.bill_id.entity.BillIdParams
import com.pmb.facilities.bill.domain.bill_id.useCase.GetBillDataBasedIdUseCase
import com.pmb.facilities.bill.domain.bill_id.useCase.GetTeleComBillDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BillViewModel @Inject constructor(
    initialState: BillViewState,
    private val getBillsDataUseCase: GetBillsDataUseCase,
    private val billDataBasedIdUseCase: GetBillDataBasedIdUseCase,
    private val getTeleComBillDataUseCase: GetTeleComBillDataUseCase
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

            is BillViewActions.SetPurchaseBottomSheetVisibility -> {
                handlePurchaseBottomSheetVisibility()
            }

            is BillViewActions.GetBillDataBasedId -> {
                handleGetBillData(action)
            }

            is BillViewActions.GetTeleComBillDataBasedId -> {
                handleTeleComBillData(action)
            }

            is BillViewActions.SetTeleComBottomSheetVisibility -> {
                handleSetTeleComBottomSheetVisibility()
            }
        }
    }

    private fun handleTeleComBillData(phoneNumber: BillViewActions.GetTeleComBillDataBasedId) {
        viewModelScope.launch {
            getTeleComBillDataUseCase.invoke(
                params = BillIdParams(phoneNumber.billId)
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
                                teleCommunicationEntity = result.data,
                                billIdEntity = null
                            )
                        }
                        handle(BillViewActions.SetTeleComBottomSheetVisibility)
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


    private fun handleSetTeleComBottomSheetVisibility() {

        viewModelScope.launch {
            setState {
                it.copy(
                    isTeleComBottomSheetVisibility = !it.isTeleComBottomSheetVisibility
                )
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

    private fun handlePurchaseBottomSheetVisibility() {
        setState {
            it.copy(
                isPurchaseBottomSheetVisibility = !it.isPurchaseBottomSheetVisibility
            )
        }
    }

    private fun handleGetBillData(data: BillViewActions.GetBillDataBasedId) {
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
                                billIdEntity = result.data,
                                teleCommunicationEntity = null
                            )
                        }
                        handle(BillViewActions.SetPurchaseBottomSheetVisibility)
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