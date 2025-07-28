package com.pmb.profile.presentaion.profile.viewModel

import androidx.lifecycle.viewModelScope
import com.pmb.ballon.models.AccountSampleModel
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.Result
import com.pmb.domain.usecae.auth.GetUserDataUseCase
import com.pmb.domain.usecae.auth.LogoutUserUseCase
import com.pmb.profile.domain.profile.useCase.ProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    initialState: ProfileViewState,
    private val logoutUserUseCase: LogoutUserUseCase,
    private val getUserDataUseCase: GetUserDataUseCase
) : BaseViewModel<ProfileViewActions, ProfileViewState, ProfileViewEvents>(initialState) {

    init {
        viewModelScope.launch {
            getUserDataUseCase.invoke(Unit).collect { result ->
                when (result) {
                    is Result.Success ->
                        setState { it.copy(userData = result.data) }

                    else -> Unit
                }
            }
        }
    }

    override fun handle(action: ProfileViewActions) {
        when (action) {
            ProfileViewActions.ClearAlert -> setState { it.copy(loading = false) }
            ProfileViewActions.LogoutAccount -> handleLogoutAccount()
            ProfileViewActions.NavigateToThemeScreen -> postEvent(ProfileViewEvents.NavigateToThemeScreen)
            ProfileViewActions.NavigateToUpdate -> postEvent(ProfileViewEvents.NavigateToUpdate)
        }
    }

    private fun handleLogoutAccount() {
        viewModelScope.launch {
            logoutUserUseCase.invoke(Unit).collect { result ->
                when (result) {
                    is Result.Success -> {
                        setState {
                            it.copy(
                                loading = false, alertModelState = AlertModelState.SnackBar(
                                    message = "",
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

}