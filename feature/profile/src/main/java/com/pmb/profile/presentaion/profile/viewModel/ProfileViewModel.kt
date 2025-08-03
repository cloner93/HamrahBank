package com.pmb.profile.presentaion.profile.viewModel

import androidx.lifecycle.viewModelScope
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.Result
import com.pmb.domain.usecae.auth.GetUserDataUseCase
import com.pmb.domain.usecae.auth.LogoutUserUseCase
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
            is ProfileViewActions.ShowSupportBottomSheet ->
                setState { it.copy(showSupportBottomSheet = action.show) }

            is ProfileViewActions.ShowInviteFriendBottomSheet ->
                setState { it.copy(showInviteFriendBottomSheet = action.show) }

            ProfileViewActions.AboutAppClicked -> postEvent(ProfileViewEvents.NavigateToAboutApp)
            ProfileViewActions.CommentsSuggestionsClicked -> postEvent(ProfileViewEvents.NavigateToCommentsSuggestions)
        }
    }

    private fun handleLogoutAccount() {
        setState {
            it.copy(
                loading = false,
                alertModelState = AlertModelState.Dialog(
                    title = "پیام",
                    description = "آیا از خروج اطمینان دارید؟",
                    positiveButtonTitle = "یله",
                    negativeButtonTitle = "خیر",
                    onPositiveClick = {
                        setState { state -> state.copy(alertModelState = null) }
                        logoutAccount()
                    },
                    onNegativeClick = {
                        setState { state -> state.copy(alertModelState = null) }
                    }
                )
            )
        }
    }

    private fun logoutAccount() {
        viewModelScope.launch {
            logoutUserUseCase.invoke(Unit).collect { result ->
                when (result) {
                    is Result.Success -> {
                        setState { it.copy(loading = false) }
                        postEvent(ProfileViewEvents.LogoutAccountSucceed)
                    }

                    Result.Loading -> setState { it.copy(loading = true) }
                    else -> Unit
                }
            }
        }
    }

}