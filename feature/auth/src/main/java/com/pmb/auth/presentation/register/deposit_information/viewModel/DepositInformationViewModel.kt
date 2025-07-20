package com.pmb.auth.presentation.register.deposit_information.viewModel

import androidx.lifecycle.viewModelScope
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.Result
import com.pmb.domain.usecae.auth.openAccount.FetchAccountTypeParams
import com.pmb.domain.usecae.auth.openAccount.FetchAccountTypeUseCase
import com.pmb.domain.usecae.auth.openAccount.FetchBranchListUseCase
import com.pmb.domain.usecae.auth.openAccount.FetchCityListParams
import com.pmb.domain.usecae.auth.openAccount.FetchCityListUseCase
import com.pmb.domain.usecae.auth.openAccount.FetchCommitmentUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DepositInformationViewModel @Inject constructor(
    initialState: DepositInformationViewState,
    private val fetchAccountTypeUseCase: FetchAccountTypeUseCase,
    private val fetchCityListUseCase: FetchCityListUseCase,
    private val fetchCommitmentUseCase: FetchCommitmentUseCase
) :
    BaseViewModel<DepositInformationViewActions, DepositInformationViewState, DepositInformationViewEvents>(
        initialState
    ) {
    override fun handle(action: DepositInformationViewActions) {
        when (action) {
            is DepositInformationViewActions.ClearAlert -> {
                setState {
                    it.copy(
                        isLoading = false
                    )
                }
            }

            is DepositInformationViewActions.SetAccountType -> {
                setState {
                    it.copy(
                        accType = action.accType
                    )
                }
            }

            is DepositInformationViewActions.SetProvince -> {
                setState {
                    it.copy(
                        province = action.province
                    )
                }
                handleFetchCityList(action.province.provinceCode)
            }

            is DepositInformationViewActions.FetchAccountType -> {
                handleFetchAccountType(action)
            }

            is DepositInformationViewActions.SetOpeningBranch -> {
                handleSetOpeningBranch(action)
            }
            is DepositInformationViewActions.SetCity -> {
                setState {
                    it.copy(
                        city = action.city
                    )
                }
            }
            is DepositInformationViewActions.SelectRules -> {
                handleSelectRules()
            }
        }
    }



    private fun handleFetchCityList(provinceCode: Int) {
        viewModelScope.launch {
            fetchCityListUseCase.invoke(
                params = FetchCityListParams(
                    stateCode = provinceCode
                )
            ).collect { result ->
                when (result) {
                    is Result.Success -> {
                        setState {
                            it.copy(
                                isLoading = false,
                                cityList = result.data.cityList
                            )
                        }
                    }

                    is Result.Loading -> {
                        setState {
                            it.copy(
                                isLoading = true
                            )
                        }
                    }

                    is Result.Error -> {
                        setState {
                            it.copy(
                                isLoading = false,
                                alertModelState = AlertModelState.Dialog(
                                    title = "خطا",
                                    description = result.message,
                                    positiveButtonTitle = "تایید",
                                    onPositiveClick = {
                                        setState { state -> state.copy(alertModelState = null) }
                                    }
                                )
                            )
                        }
                    }

                }
            }
        }
    }

    private fun handleSelectRules() {
        setState {
            it.copy(
                isChecked = true
            )
        }
    }

    private fun handleFetchAccountType(action: DepositInformationViewActions.FetchAccountType) {
        viewModelScope.launch {
            fetchAccountTypeUseCase.invoke(
                params = FetchAccountTypeParams(
                    nationalCode = action.nationalCode,
                    mobileNo = action.mobileNo
                )
            ).collect { result ->
                when (result) {
                    is Result.Success -> {
                        setState {
                            it.copy(
                                isLoading = false,
                                fetchAccountTypeResponse = result.data
                            )
                        }
                    }

                    is Result.Loading -> {
                        setState {
                            it.copy(
                                isLoading = true
                            )
                        }
                    }

                    is Result.Error -> {
                        setState {
                            it.copy(
                                isLoading = false,
                                alertModelState = AlertModelState.Dialog(
                                    title = "خطا",
                                    description = result.message,
                                    positiveButtonTitle = "تایید",
                                    onPositiveClick = {
                                        setState { state -> state.copy(alertModelState = null) }
                                    }
                                )
                            )
                        }
                    }

                }
            }
        }
    }

    private fun handleSetOpeningBranch(action: DepositInformationViewActions.SetOpeningBranch) {
        setState {
            it.copy(
                branch = action.openingBranchId
            )
        }
    }

}