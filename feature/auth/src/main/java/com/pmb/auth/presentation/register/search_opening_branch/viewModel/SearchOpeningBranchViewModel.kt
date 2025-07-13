package com.pmb.auth.presentation.register.search_opening_branch.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.pmb.auth.domain.register.search_opening_branch.entity.OpeningBranchEntity
import com.pmb.auth.domain.register.search_opening_branch.entity.OpeningBranchParams
import com.pmb.auth.domain.register.search_opening_branch.useCase.OpeningBranchUseCase
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchOpeningBranchViewModel @Inject constructor(
    initialState: SearchOpeningBranchViewState,
    private val openingBranchUseCase: OpeningBranchUseCase,
    private val savedStateHandle: SavedStateHandle
) : BaseViewModel<SearchOpeningBranchViewActions, SearchOpeningBranchViewState, SearchOpeningBranchViewEvents>(
    initialState
) {
    private val cityId = savedStateHandle.get<Int>("cityId")
    private val cityName = savedStateHandle.get<String>("cityName")
    private val provinceName = savedStateHandle.get<String>("provinceName")
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
            is SearchOpeningBranchViewActions.ClearSearchData->{
                handleClearSearchData()
            }
        }

    }

    private fun handleSearchQuery(action: SearchOpeningBranchViewActions.SearchOpeningBranchData) {
        viewState.value.originalOpeningBranch?.openingBranch?.filter { it.openingBranch.contains(action.queryString) || it.openingBranchAddress.contains(action.queryString) }
            ?.let { result ->
                setState {
                    it.copy(
                        openingBranch = OpeningBranchEntity(
                            isSuccess = true,
                            openingBranch = result
                        )
                    )
                }
            }
    }
    private fun handleClearSearchData(){
        setState {
            it.copy(
                openingBranch = it.originalOpeningBranch
            )
        }
    }
    private fun checkAndChangeBranchId(action: SearchOpeningBranchViewActions.SelectOpeningBranchId) {
        if (action.branch.id != viewState.value.selectedOpeningBranch?.id) {
            setState {
                it.copy(selectedOpeningBranch = action.branch)
            }
            viewState.value.openingBranch?.openingBranch?.findLast { it.isChecked.value }?.id?.let {
                viewState.value.openingBranch?.openingBranch?.get(it)?.apply {
                    isChecked.value = false
                }
            }
            viewState.value.openingBranch?.openingBranch?.findLast { it.id == action.branch.id }?.id?.let {
                viewState.value.openingBranch?.openingBranch?.get(it)?.apply {
                    isChecked.value = isChecked.value == false
                }
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
                openingBranchUseCase.invoke(OpeningBranchParams(it))
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
                                        openingBranch = result.data,
                                        originalOpeningBranch = result.data
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