package com.pmb.auth.presentation.register.search_opening_branch.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.Result
import com.pmb.domain.usecae.auth.openAccount.FetchBranchListParams
import com.pmb.domain.usecae.auth.openAccount.FetchBranchListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchOpeningBranchViewModel @Inject constructor(
    initialState: SearchOpeningBranchViewState,
    private val fetchBranchListUseCase: FetchBranchListUseCase,
    private val savedStateHandle: SavedStateHandle
) : BaseViewModel<SearchOpeningBranchViewActions, SearchOpeningBranchViewState, SearchOpeningBranchViewEvents>(
    initialState
) {
    private val cityId = savedStateHandle.get<Int>("cityId")
    private val cityName = savedStateHandle.get<String>("cityName")
    private val provinceName = savedStateHandle.get<String>("provinceName")
    private val provinceCode = savedStateHandle.get<Int>("provinceCode")
    override fun handle(action: SearchOpeningBranchViewActions) {
        when (action) {
            is SearchOpeningBranchViewActions.ClearAlert -> {
                setState {
                    it.copy(
                        isLoading = false
                    )
                }
            }

            is SearchOpeningBranchViewActions.SelectOpeningBranchId -> {
                checkAndChangeBranchId(action)
            }

            is SearchOpeningBranchViewActions.GetOpeningBranchData -> {
                handleGetOpeningBranch()
            }

            is SearchOpeningBranchViewActions.SearchOpeningBranchData -> {
                handleSearchQuery(action)
            }

            is SearchOpeningBranchViewActions.ClearSearchData -> {
                handleClearSearchData()
            }
        }

    }

    private fun handleSearchQuery(action: SearchOpeningBranchViewActions.SearchOpeningBranchData) {
        viewState.value.branchList?.filter {
            it.branchName.contains(action.queryString) || it.address.contains(
                action.queryString
            )
        }
            ?.let { result ->
                setState {
                    it.copy(
                        searchedBranchList = result
                    )
                }
            }
    }

    private fun handleClearSearchData() {
        setState {
            it.copy(
                searchedBranchList = it.branchList
            )
        }
    }

    private fun checkAndChangeBranchId(action: SearchOpeningBranchViewActions.SelectOpeningBranchId) {
        if (action.branch.branchCode != viewState.value.selectedBranch?.branchCode) {
            setState {
                it.copy(selectedBranch = action.branch)
            }
        }
    }

    private fun setHeadTitle() {
        setState {
            it.copy(
                headTitle = " $provinceName , $cityName "
            )
        }
    }

    private fun handleGetOpeningBranch() {
        cityId?.let {
            viewModelScope.launch {
                fetchBranchListUseCase.invoke(FetchBranchListParams(provinceCode ?: -1, it))
                    .collect { result ->
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
                                        branchList = result.data,
                                        searchedBranchList = result.data
                                    )
                                }
                            }
                        }
                    }
            }
        }
    }

    init {
        handle(SearchOpeningBranchViewActions.GetOpeningBranchData)
        setHeadTitle()
    }
}