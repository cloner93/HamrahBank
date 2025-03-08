package com.pmb.auth.presentation.register.deposit_information.viewModel

import androidx.lifecycle.viewModelScope
import com.pmb.auth.domain.register.deposit_information.entity.BranchCityParams
import com.pmb.auth.domain.register.deposit_information.entity.SendDepositInformationParams
import com.pmb.auth.domain.register.deposit_information.useCase.BranchCityUseCase
import com.pmb.auth.domain.register.deposit_information.useCase.DepositInformationUseCase
import com.pmb.auth.domain.register.deposit_information.useCase.SendDepositInformationUseCase
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DepositInformationViewModel @Inject constructor(
    initialState: DepositInformationViewState,
    private val branchCityUseCase: BranchCityUseCase,
    private val depositInformationUseCase: DepositInformationUseCase,
    private val sendDepositInformationUseCase: SendDepositInformationUseCase,
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

            is DepositInformationViewActions.GetBranchCity -> {
                handleBranchCity(action)
            }

            is DepositInformationViewActions.GetDepositInformation -> {
                handleDepositInformation()
            }

            is DepositInformationViewActions.SetCityId -> {
                handleSetCityId(action)
            }

            is DepositInformationViewActions.SendDepositInformation -> {
                handleSendDepositInformation(action)
            }

            is DepositInformationViewActions.DepositType -> {
                handleDepositType(action)
            }

            is DepositInformationViewActions.SetOpeningBranch -> {
                handleSetOpeningBranch(action)
            }

            is DepositInformationViewActions.AnnualIncomingPrediction -> {
                handleAnnualIncomingPrediction(action)
            }

        }
    }

    private fun handleSetCityId(action: DepositInformationViewActions.SetCityId) {
        setState {
            it.copy(
                sendDepositInformationParams = it.sendDepositInformationParams?.copy(
                    branchCity = action.cityId
                ) ?: run {
                    SendDepositInformationParams(
                        annualIncomingPrediction = null,
                        branchProvince = null,
                        openingBranch = null,
                        branchCity = action.cityId,
                        depositType = null
                    )
                }
            )
        }
    }

    private fun handleAnnualIncomingPrediction(action: DepositInformationViewActions.AnnualIncomingPrediction) {
        setState {
            it.copy(
                sendDepositInformationParams = it.sendDepositInformationParams?.copy(
                    annualIncomingPrediction = action.annualIncomingPrediction
                ) ?: run {
                    SendDepositInformationParams(
                        branchCity = null,
                        branchProvince = null,
                        openingBranch = null,
                        annualIncomingPrediction = action.annualIncomingPrediction,
                        depositType = null
                    )
                }
            )
        }
    }

    private fun handleSetOpeningBranch(action: DepositInformationViewActions.SetOpeningBranch) {
        setState {
            it.copy(
                openedBranch = action.openingBranchId,
                sendDepositInformationParams = it.sendDepositInformationParams?.copy(
                    openingBranch = action.openingBranchId.id
                ) ?: run {
                    SendDepositInformationParams(
                        branchCity = null,
                        branchProvince = null,
                        annualIncomingPrediction = null,
                        openingBranch = action.openingBranchId.id,
                        depositType = null
                    )
                }
            )
        }
    }

    private fun handleDepositType(action: DepositInformationViewActions.DepositType) {
        setState {
            it.copy(
                sendDepositInformationParams = it.sendDepositInformationParams?.copy(
                    depositType = action.depositType
                ) ?: run {
                    SendDepositInformationParams(
                        branchCity = null,
                        branchProvince = null,
                        openingBranch = null,
                        annualIncomingPrediction = null,
                        depositType = action.depositType
                    )
                }
            )
        }
    }

    private fun handleSendDepositInformation(action: DepositInformationViewActions.SendDepositInformation) {
        viewModelScope.launch {
            sendDepositInformationUseCase.invoke(
                action.sendDepositInformationParams
            ).collect { result ->
                when (result) {
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
                                alertModelState = AlertModelState.SnackBar(
                                    message = result.message,
                                    onActionPerformed = {
                                        setState { state -> state.copy(alertModelState = null) }
                                    },
                                    onDismissed = {
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
                            )
                        }
                        postEvent(DepositInformationViewEvents.SendDepositInformationSucceeded)
                    }
                }

            }
        }
    }

    private fun handleBranchCity(action: DepositInformationViewActions.GetBranchCity) {
        viewModelScope.launch {
            branchCityUseCase.invoke(
                BranchCityParams(id = action.provinceId)
            ).collect { result ->
                when (result) {
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
                                alertModelState = AlertModelState.SnackBar(
                                    message = result.message,
                                    onActionPerformed = {
                                        setState { state -> state.copy(alertModelState = null) }
                                    },
                                    onDismissed = {
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
                                branchCity = result.data,
                                sendDepositInformationParams = it.sendDepositInformationParams?.copy(
                                    branchProvince = action.provinceId
                                ) ?: run {
                                    SendDepositInformationParams(
                                        branchProvince = action.provinceId,
                                        branchCity = null,
                                        openingBranch = null,
                                        annualIncomingPrediction = null,
                                        depositType = null
                                    )
                                }
                            )
                        }
                    }
                }
            }
        }
    }


    private fun handleDepositInformation() {
        viewModelScope.launch {
            depositInformationUseCase.invoke(Unit).collect { result ->
                when (result) {
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
                                alertModelState = AlertModelState.SnackBar(
                                    message = result.message,
                                    onActionPerformed = {
                                        setState { state -> state.copy(alertModelState = null) }
                                    },
                                    onDismissed = {
                                        setState { state -> state.copy(alertModelState = null) }
                                    }
                                )

                            )
                        }
                    }

                    is Result.Success -> {
                        setState {
                            it.copy(
                                isLoading = false, depositInformation = result.data
                            )
                        }
                    }
                }

            }
        }
    }

    init {
        handle(DepositInformationViewActions.GetDepositInformation)
    }
}