package com.pmb.profile.presentaion.profile.viewModel

import androidx.lifecycle.viewModelScope
import com.pmb.ballon.models.AccountSampleModel
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.Result
import com.pmb.profile.domain.profile.useCase.ProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    initialState: ProfileViewState,
    private val profileUseCase: ProfileUseCase
) : BaseViewModel<ProfileViewActions, ProfileViewState, ProfileViewEvents>(initialState) {
    override fun handle(action: ProfileViewActions) {
        when (action) {
            ProfileViewActions.ClearAlert -> setState { it.copy(loading = false) }
            ProfileViewActions.LogoutAccount -> handleLogoutAccount()
        }
    }

    private fun handleLogoutAccount() {
        viewModelScope.launch {
            profileUseCase.invoke(Unit).collect { result ->
                when (result) {
                    is Result.Success -> {
                        setState {
                            it.copy(loading = false, alertModelState = AlertModelState.SnackBar(
                                message = result.data,
                                onActionPerformed = {
                                    setState { state -> state.copy(alertModelState = null) }
                                },
                                onDismissed = {
                                    setState { state -> state.copy(alertModelState = null) }
                                }
                            ), userData = null)
                        }
                        postEvent(ProfileViewEvents.LogoutAccountSucceed)
                    }

                    Result.Loading -> setState { it.copy(loading = true) }
                    else -> Unit
                }
            }
        }
    }

    private fun getUserData() {
        setState {
            it.copy(
                userData = AccountSampleModel()
            )
        }
    }

    init {
        getUserData()
    }

}