package com.pmb.home.presentation.home.viewModel

import androidx.lifecycle.viewModelScope
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.Result
import com.pmb.home.domain.home.useCase.GetHomeDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    initialState: HomeViewState,
    private val getHomeDataUseCase: GetHomeDataUseCase
) : BaseViewModel<HomeViewActions, HomeViewState, HomeViewEvents>(initialState) {
    override fun handle(action: HomeViewActions) {
        when (action) {
            HomeViewActions.ClearAlert -> {
                setState {
                    it.copy(
                        isLoading = false
                    )
                }
            }

            HomeViewActions.GetData -> {
                handleGetHomeData()
            }
        }
    }

    private fun handleGetHomeData() {
        viewModelScope.launch {
            getHomeDataUseCase.invoke(Unit).collectLatest { result ->
                when (result) {
                    Result.Loading -> {
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
                            it.copy(isLoading = false, homeData = result.data)
                        }
                    }
                }
            }
        }

    }

    init {
        handle(HomeViewActions.GetData)
    }
}