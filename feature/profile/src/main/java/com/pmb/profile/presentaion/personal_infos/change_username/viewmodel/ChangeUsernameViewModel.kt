package com.pmb.profile.presentaion.personal_infos.change_username.viewmodel

import androidx.lifecycle.viewModelScope
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.Result
import com.pmb.data.serviceProvider.local.LocalServiceProvider
import com.pmb.profile.domain.use_case.ChangeUsernameUseCase
import com.pmb.profile.presentaion.personal_infos.PersonalInfoSharedState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChangeUsernameViewModel @Inject constructor(
    private val changeUsernameUseCase: ChangeUsernameUseCase,
    private val localProvider: LocalServiceProvider
) : BaseViewModel<ChangeUsernameViewActions, ChangeUsernameViewState, ChangeUsernameViewEvents>(
    ChangeUsernameViewState()
) {

    override fun handle(action: ChangeUsernameViewActions) {
        when (action) {
            is ChangeUsernameViewActions.UpdateShareState -> handleUpdateShareState(action.sharedState)
            ChangeUsernameViewActions.ClearAlert -> setState { it.copy(alertState = null) }
            is ChangeUsernameViewActions.UsernameChanged -> handleUsernameChanged(action.username)
            ChangeUsernameViewActions.SubmitUsername -> updateUsername()
        }
    }

    private fun handleUpdateShareState(sharedState: PersonalInfoSharedState) {
        viewModelScope.launch {
            localProvider.getUserDataStore().getUserData()?.username?.let { username ->
                setState { it.copy(username = username) }
            }
        }
    }

    private fun updateUsername() {
        val username = viewState.value.username
        viewModelScope.launch {
            changeUsernameUseCase.invoke(
                ChangeUsernameUseCase.Param(
                    userId = 10L, username = username
                )
            ).collect { result ->
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
                        localProvider.getUserDataStore().getUserData()?.copy(username = username)
                            ?.let {
                                localProvider.getUserDataStore().setUserData(userData = it)
                            }
                        setState { it.copy(loading = false) }
                        postEvent(ChangeUsernameViewEvents.NavigateBackToPersonalInfo(newUsername = username))
                    }
                }
            }
        }
    }

    private fun handleUsernameChanged(value: String) {
        setState { it.copy(username = value) }
    }
}