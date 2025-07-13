package com.pmb.auth.presentation.register.job_information.viewModel

import androidx.lifecycle.viewModelScope
import com.pmb.auth.domain.register.job_information.entity.JobInformationParam
import com.pmb.auth.domain.register.job_information.useCase.GetAnnualIncomePrediction
import com.pmb.auth.domain.register.job_information.useCase.SendJobInformationUseCase
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class JobInformationViewModel @Inject constructor(
    initialState: JobInformationViewState,
    private val getAnnualIncomePrediction: GetAnnualIncomePrediction,
    private val sendJobInformationUseCase: SendJobInformationUseCase
) :
    BaseViewModel<JobInformationViewActions, JobInformationViewState, JobInformationViewEvents>(
        initialState
    ) {
    override fun handle(action: JobInformationViewActions) {
        when (action) {
            is JobInformationViewActions.ClearAlert -> {
                setState {
                    it.copy(
                        isLoading = false
                    )
                }
            }

            is JobInformationViewActions.SetJobInformation -> {
                handleSetJobInformation(action)
            }

            is JobInformationViewActions.GetAnnualIncomePrediction -> {
                handleGetAnnualIncomePrediction()
            }

            is JobInformationViewActions.SendJonInformation -> {
                handleSendJobInformation(action)
            }
        }
    }

    private fun handleSetJobInformation(action: JobInformationViewActions.SetJobInformation) {
        setState {
            it.copy(
                jobInformation = action.jobInformation
            )
        }
    }

    private fun handleGetAnnualIncomePrediction() {
        viewModelScope.launch {
            getAnnualIncomePrediction.invoke(Unit).collect { result ->
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
                                isLoading = false, alertModelState = AlertModelState.Dialog(
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
                                isLoading = false,
                                data = result.data
                            )
                        }
                    }
                }
            }
        }
    }

    private fun handleSendJobInformation(action: JobInformationViewActions.SendJonInformation) {
        viewModelScope.launch {
            sendJobInformationUseCase.invoke(
                JobInformationParam(
                    annualIncomeId = action.annualId,
                    jobId = action.jobId
                )
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
                                isLoading = false, alertModelState = AlertModelState.Dialog(
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
                                isLoading = false,
                            )
                        }
                        postEvent(JobInformationViewEvents.SendJobInformationSucceed)
                    }
                }
            }
        }
    }

    init {
        handle(JobInformationViewActions.GetAnnualIncomePrediction)
    }
}