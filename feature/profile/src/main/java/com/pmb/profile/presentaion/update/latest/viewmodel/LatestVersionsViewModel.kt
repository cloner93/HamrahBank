package com.pmb.profile.presentaion.update.latest.viewmodel

import androidx.lifecycle.viewModelScope
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.Result
import com.pmb.profile.domain.entity.VersionEntity
import com.pmb.profile.domain.use_case.FetchVersionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LatestVersionsViewModel @Inject constructor(
    private val fetchVersionUseCase: FetchVersionsUseCase,
) : BaseViewModel<LatestVersionsViewActions, LatestVersionsViewState, LatestVersionsViewEvents>(
    LatestVersionsViewState()
) {

    init {
        fetchLatestVersions()
    }

    override fun handle(action: LatestVersionsViewActions) {
        when (action) {
            LatestVersionsViewActions.ClearAlert -> setState { it.copy(alertState = null) }
            is LatestVersionsViewActions.SelectVersion -> {
                handleUpdateClicked(action.versionEntity)
            }
        }
    }

    private fun handleUpdateClicked(entity: VersionEntity) {
        postEvent(LatestVersionsViewEvents.NavigateToDetail(entity))
    }


    private fun fetchLatestVersions() {
        viewModelScope.launch {
            fetchVersionUseCase.invoke(Unit).collect { result ->
                when (result) {
                    is Result.Error -> {
                        setState {
                            it.copy(
                                loading = false,
                                alertState = AlertModelState.SnackBar(
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
                                loading = false,
                                versionEntities = result.data
                            )
                        }
                    }
                }
            }
        }
    }
}