package com.pmb.profile.presentaion.personal_infos.change_username.viewmodel

import androidx.lifecycle.viewModelScope
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.Result
import com.pmb.data.serviceProvider.local.LocalServiceProvider
import com.pmb.profile.domain.use_case.ChangeUsernameUseCase
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
            is ChangeUsernameViewActions.UpdateShareState -> Unit
            ChangeUsernameViewActions.ClearAlert -> setState { it.copy(alertState = null) }
            is ChangeUsernameViewActions.UsernameChanged -> handleUsernameChanged(action.username)
            ChangeUsernameViewActions.SubmitUsername -> updateUsername()
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
                                alertState = AlertModelState.Dialog(
                                    title = "خطا",
                                    description = " ${result.message}",
                                    positiveButtonTitle = "تایید",
                                    onPositiveClick = {
                                        setState { state -> state.copy(alertState = null) }
                                    }
                                )
                            )
                        }
                    }

                    Result.Loading -> {
                        setState { it.copy(loading = true) }
                    }

                    is Result.Success -> {
                        localProvider.getUserDataStore().getUserData().copy(username = username)
                            .let {
                                localProvider.getUserDataStore().setUserData(userData = it)
                            }
                        setState {
                            it.copy(
                                loading = false,
                                alertState = AlertModelState.Dialog(
                                    title = "پیغام",
                                    description = "نام کاربری شما با موفقیت تغییر یافت!",
                                    positiveButtonTitle = "تایید",
                                    onPositiveClick = {
                                        setState { state -> state.copy(alertState = null) }
                                        postEvent(
                                            ChangeUsernameViewEvents.NavigateBackToPersonalInfo(
                                                newUsername = username
                                            )
                                        )
                                    }
                                ))
                        }
                    }
                }
            }
        }
    }

    private fun handleUsernameChanged(value: String) {
        setState { it.copy(username = value) }
    }
}