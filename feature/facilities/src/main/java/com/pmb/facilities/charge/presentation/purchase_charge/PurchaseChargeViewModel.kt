package com.pmb.facilities.charge.presentation.purchase_charge

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.pmb.core.permissions.PermissionDispatcher
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.Result
import com.pmb.facilities.charge.domain.purchase_charge.useCase.GetOperatorUseCase
import com.pmb.facilities.utils.SimOperatorDetector
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PurchaseChargeViewModel @Inject constructor(
    private val permissionDispatcher: PermissionDispatcher,
    private val getOperatorUseCase: GetOperatorUseCase
) : BaseViewModel<PurchaseChargeViewActions, PurchaseChargeViewState, PurchaseChargeViewEvents>(
    PurchaseChargeViewState()
) {

    override fun handle(action: PurchaseChargeViewActions) {
        when (action) {
            is PurchaseChargeViewActions.GetOperatorData -> {
                handleGetOperatorData()
            }

            is PurchaseChargeViewActions.RequestContactPermission -> {
                requestContactPermission(action)
            }

            is PurchaseChargeViewActions.SetMobileNumber -> {
                setMobileNumber(action)
            }

            is PurchaseChargeViewActions.ClearMobileOperator -> {
                handleClearMobileOperator()
            }
            is PurchaseChargeViewActions.SetMobileOperator -> {
                handleSetMobileOperator()
            }
        }
    }

    private fun handleSetMobileOperator() {
        val operator = SimOperatorDetector.detectOperator(viewState.value.mobile)
        viewState.value.operatorData?.findLast { it.isChecked.value }?.apply {
            isChecked.value = false
        }
        viewState.value.operatorData?.findLast { it.id == operator?.id  }?.apply {
            isChecked.value = true
        }?.also { op->
            setState {
                it.copy(operator =op)
            }
        }
    }

    private fun handleClearMobileOperator() {
        viewState.value.operator?.apply {
            null
        }
        viewState.value.operatorData?.findLast { it.isChecked.value }?.apply {
            isChecked.value = false
        }
    }

    private fun handleGetOperatorData() {
        viewModelScope.launch {
            getOperatorUseCase.invoke(Unit).collectLatest { result ->
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
                                operatorData = result.data.data
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

    private fun setMobileNumber(number: PurchaseChargeViewActions.SetMobileNumber) {
        setState {
            it.copy(mobile = number.mobileNumber)
        }
    }

    private fun requestContactPermission(action: PurchaseChargeViewActions.RequestContactPermission) {
        viewModelScope.launch {

            permissionDispatcher.initialize(action.managedActivityResultLauncher)
            permissionDispatcher.requestSinglePermission(
                permission = android.Manifest.permission.READ_CONTACTS,
                onPermissionGranted = {
                    postEvent(PurchaseChargeViewEvents.OpenContactList)
                },
                onPermissionDenied = {
                    Log.i("per", "You don't have permission for using camera")
                }
            )
        }
    }

    fun onSinglePermissionResult(isGranted: Boolean) {
        permissionDispatcher.onSinglePermissionResult(isGranted)

    }

    init {
        handle(PurchaseChargeViewActions.GetOperatorData)
    }
}