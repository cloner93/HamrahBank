package com.pmb.auth.presentation.reentry.reentry_password.viewModel

import androidx.lifecycle.viewModelScope
import com.pmb.auth.domain.login.usecase.RequestLoginParams
import com.pmb.auth.domain.login.usecase.RequestLoginUseCase
import com.pmb.ballon.models.AccountSampleModel
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReentryPasswordViewModel @Inject constructor(
    initialState: ReentryPasswordViewState,
    private val loginRequestLoginUseCase: RequestLoginUseCase,
) : BaseViewModel<ReentryPasswordViewActions, ReentryPasswordViewState, ReentryPasswordViewEvents>(
    initialState
) {
    override fun handle(action: ReentryPasswordViewActions) {
        when(action){
            is ReentryPasswordViewActions.ClearAlert->{
                setState {
                    it.copy(
                        isLoading = false
                    )
                }
            }
            is ReentryPasswordViewActions.ReentryPassword->{
                handleLogin(action)
            }
        }
    }

    private fun handleLogin(action: ReentryPasswordViewActions.ReentryPassword) {
        viewModelScope.launch {
            loginRequestLoginUseCase.invoke(
                RequestLoginParams(
                    username = viewState.value.userData?.userName ?: "", password = action.password
                )
            ).collect { result ->
                when (result) {
                    is Result.Error -> {
                        setState {
                            it.copy(
                                isLoading = false, alertModelState = AlertModelState.Dialog(
                                    title = "Login Failed",
                                    description = "failed to login because ${result.message}",
                                    positiveButtonTitle = "okay",
                                    onPositiveClick = {
                                        setState { state -> state.copy(alertModelState = null) }
                                    }
                                )
                            )
                        }
                    }

                    Result.Loading -> setState { it.copy(isLoading = true) }
                    is Result.Success -> {
                        setState { it.copy(isLoading = false) }
                        postEvent(ReentryPasswordViewEvents.ReentryPasswordSucceed)
                    }
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