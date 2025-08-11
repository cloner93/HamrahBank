package com.pmb.profile.presentaion.privacyAndSecurity.changePassword.viewmodel

import androidx.lifecycle.viewModelScope
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.Result
import com.pmb.data.serviceProvider.local.LocalServiceProvider
import com.pmb.profile.domain.use_case.ChangePasswordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChangePasswordViewModel @Inject constructor(
    private val changePasswordUseCase: ChangePasswordUseCase,
    private val localProvider: LocalServiceProvider
) : BaseViewModel<ChangePasswordViewActions, ChangePasswordViewState, ChangePasswordViewEvents>(
    ChangePasswordViewState()
) {
    override fun handle(action: ChangePasswordViewActions) {
        when (action) {
            is ChangePasswordViewActions.SetOldPassword -> {
                setState {
                    it.copy(
                        oldPassword = action.oldPassword,
                        isOldPasswordValid = action.oldPassword.isNotEmpty()
                    )
                }
                validatePassword()
            }

            is ChangePasswordViewActions.SetNewPassword -> {
                setState {
                    it.copy(
                        newPassword = action.newPassword
                    )
                }
            }

            is ChangePasswordViewActions.SetNewPasswordValid -> {
                setState {
                    it.copy(
                        isNewPasswordValid = action.isNewPasswordValid
                    )
                }
                validatePassword()
            }

            is ChangePasswordViewActions.SetRenewPassword -> {
                setState {
                    it.copy(
                        renewPassword = action.renewPassword,
                        isRenewPasswordValid = action.renewPassword.isNotEmpty() && viewState.value.newPassword == action.renewPassword
                    )
                }
                validatePassword()
            }

            ChangePasswordViewActions.SubmitNewPassword -> if (validatePassword()) updatePassword()
        }
    }

    private fun validatePassword(): Boolean {
        val isAllPasswordOk =
            viewState.value.isOldPasswordValid && viewState.value.isNewPasswordValid && viewState.value.isRenewPasswordValid

        setState {
            it.copy(
                isAllPasswordOk = isAllPasswordOk
            )
        }
        return isAllPasswordOk
    }

    private fun updatePassword() {
        viewModelScope.launch {
            val customerId =
                localProvider.getUserDataStore().getUserData().customerId
            changePasswordUseCase.invoke(
                ChangePasswordUseCase.Param(
                    userId = customerId,
                    oldPassword = viewState.value.oldPassword,
                    newPassword = viewState.value.newPassword
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
                        setState { it.copy(loading = false) }
                        postEvent(ChangePasswordViewEvents.ShowSnackBar(message = "رمز عبور شما با موفقیت تغییر یافت!"))
                        postEvent(ChangePasswordViewEvents.NavigateBack)
                    }
                }
            }
        }
    }
}