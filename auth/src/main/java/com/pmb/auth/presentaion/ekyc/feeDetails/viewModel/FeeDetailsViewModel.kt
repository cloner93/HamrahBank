package com.pmb.auth.presentaion.ekyc.feeDetails.viewModel

import androidx.lifecycle.viewModelScope
import com.pmb.auth.domain.ekyc.feeDetails.useCase.GetFeeDetailsUseCase
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
                                alertModelState = AlertModelState.SnackBar(
                                    message = result.message,
                                    onActionPerformed = {
                                        setState {
                                            it.copy(
                                                loading = false
                                            )
                                        }
                                    },
                                    onDismissed = {
                                        setState {
                                            it.copy(
                                                loading = false
                                            )
                                        }
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
        getFeeDetails()
    }
}