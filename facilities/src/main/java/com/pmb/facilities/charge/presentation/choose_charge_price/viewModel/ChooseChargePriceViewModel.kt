package com.pmb.facilities.charge.presentation.choose_charge_price.viewModel

import androidx.lifecycle.viewModelScope
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.Result
import com.pmb.facilities.charge.domain.choose_charge_price.useCase.GetChargePriceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChooseChargePriceViewModel @Inject constructor(
    initialState: ChooseChargePriceViewState,
    private val getChargePriceUseCase: GetChargePriceUseCase
) : BaseViewModel<ChooseChargePriceViewActions, ChooseChargePriceViewState, ChooseChargePriceViewEvents>(
    initialState
) {
    override fun handle(action: ChooseChargePriceViewActions) {
        when (action) {
            is ChooseChargePriceViewActions.ClearAlertModelState -> {
                setState {
                    it.copy(
                        isLoading = false
                    )
                }
            }

            is ChooseChargePriceViewActions.GetChargePrice -> {
                handleGetChargePrice()
            }

            is ChooseChargePriceViewActions.SetSelectedPrice -> {
                handleSelectedPrice(action)
            }
        }
    }

    private fun handleSelectedPrice(price: ChooseChargePriceViewActions.SetSelectedPrice) {
        viewModelScope.launch {
            if (price.choosePrice.id != viewState.value.selectedPrice?.id) {
                viewState.value.chargePriceData?.findLast { !it.isChecked.value && it.id == price.choosePrice.id }
                    ?.apply {
                        isChecked.value = true
                    }
                viewState.value.chargePriceData?.findLast { it.id == viewState.value.selectedPrice?.id }
                    ?.apply {
                        isChecked.value = false
                    }
                setState {
                    it.copy(
                        selectedPrice = price.choosePrice
                    )
                }
            }
        }
    }

    private fun handleGetChargePrice() {
        viewModelScope.launch {
            getChargePriceUseCase.invoke(Unit).collectLatest { result ->
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
                                chargePriceData = result.data.data
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
        handle(ChooseChargePriceViewActions.GetChargePrice)
    }
}