package com.pmb.auth.presentation.register.select_job_information.viewModel

import androidx.lifecycle.viewModelScope
import com.pmb.auth.domain.register.select_job_information.entity.SelectJobInformationEntity
import com.pmb.auth.domain.register.select_job_information.useCase.GetJobInformationUseCase
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelectJobInformationViewModel @Inject constructor(
    initialState: SelectJobInformationViewState,
    private val getJobInformationUseCase: GetJobInformationUseCase
) : BaseViewModel<SelectJobInformationViewActions, SelectJobInformationViewState, SelectJobInformationViewEvents>(
    initialState
) {
    override fun handle(action: SelectJobInformationViewActions) {
        when (action) {
            is SelectJobInformationViewActions.SearchSelectJobInformationData -> {
                handleSearchQuery(action)
            }

            is SelectJobInformationViewActions.GetSelectJobInformation -> {
                handleGetJobInformation()
            }

            is SelectJobInformationViewActions.ClearSearchData -> {
                handleClearSearchData()
            }
        }
    }

    private fun handleGetJobInformation() {
        viewModelScope.launch {
            getJobInformationUseCase.invoke(Unit).collect { result ->
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
                                selectJobInformation = result.data,
                                originalSelectJobInformation = result.data
                            )
                        }
                    }
                }
            }
        }
    }

    private fun handleSearchQuery(action: SelectJobInformationViewActions.SearchSelectJobInformationData) {
        viewState.value.originalSelectJobInformation?.selectJobInformation?.filter {
            it.jobInformation.contains(
                action.queryString
            )
        }
            ?.let { result ->
                setState {
                    it.copy(
                        selectJobInformation = SelectJobInformationEntity(
                            isSuccess = true,
                            selectJobInformation = result
                        )
                    )
                }
            }
    }

    private fun handleClearSearchData() {
        setState {
            it.copy(
                selectJobInformation = it.originalSelectJobInformation
            )
        }
    }
    init {
        handle(SelectJobInformationViewActions.GetSelectJobInformation)
    }
}