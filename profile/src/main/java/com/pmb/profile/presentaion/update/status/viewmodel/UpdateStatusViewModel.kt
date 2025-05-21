package com.pmb.profile.presentaion.update.status.viewmodel

import androidx.lifecycle.viewModelScope
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.Result
import com.pmb.profile.domain.use_case.CheckUpdateUseCase
import com.pmb.profile.domain.use_case.UpdateVersionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class UpdateStatusViewModel @Inject constructor(
    private val checkUpdateUseCase: CheckUpdateUseCase,
    private val updateVersionUseCase: UpdateVersionUseCase
) : BaseViewModel<UpdateStatusViewActions, UpdateStatusViewState, UpdateStatusViewEvents>(
    UpdateStatusViewState()
) {
    init {
        handleCheckUpdate()
    }

    override fun handle(action: UpdateStatusViewActions) {
        when (action) {
            UpdateStatusViewActions.ClearAlert -> setState { it.copy(alert = null) }
            UpdateStatusViewActions.UpdateClicked -> handleUpdateVersionClicked()
            UpdateStatusViewActions.LatestVersion -> postEvent(UpdateStatusViewEvents.NavigateToUpdateHistory)
            UpdateStatusViewActions.ShowNewChanges -> viewState.value.versionEntity?.let {
                postEvent(UpdateStatusViewEvents.NavigateToDetail(it))
            }
        }
    }

    private fun handleCheckUpdate() {
        viewModelScope.launch {
            checkUpdateUseCase.invoke(Unit).collect { result ->
                when (result) {
                    is Result.Error -> {
                        setState {
                            it.copy(
                                loading = false,
                                alert = AlertModelState.SnackBar(
                                    message = result.message,
                                    onActionPerformed = {
                                        setState { it.copy(loading = false) }
                                    },
                                    onDismissed = {
                                        setState { it.copy(loading = false) }
                                    })
                            )
                        }
                    }

                    Result.Loading -> {
                        setState { it.copy(loading = true) }
                    }

                    is Result.Success -> {
                        setState {
                            it.copy(
                                loading = false, versionEntity = result.data
                            )
                        }
                    }
                }
            }
        }
    }


    private fun handleUpdateVersionClicked() {
        viewModelScope.launch {
            updateVersionUseCase.invoke(Unit).collect { result ->
                when (result) {
                    is Result.Error -> {
                        setState {
                            it.copy(
                                loading = false,
                                alert = AlertModelState.SnackBar(
                                    message = result.message,
                                    onActionPerformed = {
                                        setState { it.copy(loading = false) }
                                    },
                                    onDismissed = {
                                        setState { it.copy(loading = false) }
                                    })
                            )
                        }
                    }

                    Result.Loading -> {
                        setState { it.copy(loading = true) }
                    }

                    is Result.Success -> {
                        setState {
                            it.copy(
                                loading = false, versionEntity = result.data
                            )
                        }
                    }
                }
            }
        }
    }
}