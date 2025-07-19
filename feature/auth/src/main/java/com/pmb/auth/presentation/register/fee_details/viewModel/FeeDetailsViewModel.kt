package com.pmb.auth.presentation.register.fee_details.viewModel

import androidx.lifecycle.viewModelScope
import com.pmb.auth.domain.ekyc.fee_details.useCase.GetFeeDetailsUseCase
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewEvent
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeeDetailsViewModel @Inject constructor(
    initialState: FeeDetailsViewState,
    private var getFeeDetailsUseCase: GetFeeDetailsUseCase
) : BaseViewModel<FeeDetailsViewActions, FeeDetailsViewState, BaseViewEvent>(
    initialState = initialState
) {
    override fun handle(action: FeeDetailsViewActions) {
        when (action) {
            FeeDetailsViewActions.ClearAlert -> setState { it.copy(loading = false) }
            FeeDetailsViewActions.LoadFeeDetails ->{
                getFeeDetails()
            }
        }
    }

    private fun getFeeDetails() {
        viewModelScope.launch {
            getFeeDetailsUseCase.invoke(Unit).collect { result ->
                when (result) {
                    is Result.Error -> {
                        setState {
                            it.copy(
                                loading = false,
                                alertModelState =AlertModelState.Dialog(
                                    title = "خطا",
                                    description = " ${result.message}",
                                    positiveButtonTitle = "تایید",
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
                                loading = false,
                                data = result.data
                            )
                        }
                    }

                    is Result.Loading -> {
                        setState {
                            it.copy(loading = true)
                        }
                    }
                }
            }
        }
    }

    init {
        handle(FeeDetailsViewActions.LoadFeeDetails)
    }
}