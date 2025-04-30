package com.pmb.facilities.charge.presentation.charge

import androidx.lifecycle.viewModelScope
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.Result
import com.pmb.facilities.charge.domain.charge.useCase.GetLatestChargeUseCase
import com.pmb.facilities.charge.domain.purchase_charge.entity.Operator
import com.pmb.facilities.utils.SimOperatorDetector
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChargeViewModel @Inject constructor(
    initialState: ChargeViewState,
    private val getLatestChargeUseCase: GetLatestChargeUseCase
) :
    BaseViewModel<ChargeViewActions, ChargeViewState, ChargeViewEvents>(initialState) {

    override fun handle(action: ChargeViewActions) {
        when (action) {
            is ChargeViewActions.GetLatestChargeHistory -> {
                handleGetLatestData()
            }

            is ChargeViewActions.ClearAlertModelState -> {
                setState {
                    it.copy(
                        isLoading = false
                    )
                }
            }
            is ChargeViewActions.SetSelectedSimNumber ->{
                handleSetSelectedSimNumber(action)
            }
        }
    }

    private fun handleSetSelectedSimNumber(number: ChargeViewActions.SetSelectedSimNumber) {
        val op = SimOperatorDetector.detectOperator(number.simNumber.subTitle)
        setState {
            it.copy(
                selectedSim = number.simNumber.subTitle,
                operator = Operator(
                    id = op?.id ?:-1,
                    operator = op?.operatorName?:"",
                    operatorImage = number.simNumber.imageString
                )
            )
        }
        postEvent(ChargeViewEvents.UseTheLatestNumber)
    }

    private fun handleGetLatestData() {
        viewModelScope.launch {
            getLatestChargeUseCase.invoke(Unit).collectLatest { result ->
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
                                simCardList = result.data.data
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
        handle(ChargeViewActions.GetLatestChargeHistory)
    }
}