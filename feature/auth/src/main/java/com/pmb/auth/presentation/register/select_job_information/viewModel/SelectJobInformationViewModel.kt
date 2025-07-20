package com.pmb.auth.presentation.register.select_job_information.viewModel

import androidx.lifecycle.viewModelScope
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.Result
import com.pmb.domain.usecae.auth.openAccount.FetchLevelJobUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelectJobInformationViewModel @Inject constructor(
    initialState: SelectJobInformationViewState,
    private val fetchLevelJobUseCase: FetchLevelJobUseCase,
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
            fetchLevelJobUseCase.invoke().collect { result ->
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
                                alertModelState = AlertModelState.Dialog(
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
                                selectJobInformation = result.data.jobList,
                                originalSelectJobInformation = result.data.jobList
                            )
                        }
                    }
                }
            }
        }
    }

    private fun handleSearchQuery(action: SelectJobInformationViewActions.SearchSelectJobInformationData) {
        setState {
            it.copy(
                searchValue = true
            )
        }
        viewState.value.originalSelectJobInformation?.filter {
            it.jobName.contains(
                action.queryString
            )
        }
            ?.let { result ->
                setState {
                    it.copy(
                        searchValue = false,
                        selectJobInformation = result
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