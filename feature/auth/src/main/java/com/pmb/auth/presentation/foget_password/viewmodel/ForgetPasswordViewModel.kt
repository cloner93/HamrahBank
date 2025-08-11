package com.pmb.auth.presentation.foget_password.viewmodel

import androidx.lifecycle.viewModelScope
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.Result
import com.pmb.domain.usecae.auth.newPassword.NewPasswordParams
import com.pmb.domain.usecae.auth.newPassword.NewPasswordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForgetPasswordViewModel @Inject constructor(
    private val newPasswordUseCase: NewPasswordUseCase
) : BaseViewModel<ForgetPasswordViewActions, ForgetPasswordViewState, ForgetPasswordViewEvents>(
    ForgetPasswordViewState()
) {
    override fun handle(action: ForgetPasswordViewActions) {
        when (action) {
            ForgetPasswordViewActions.ClearAlert -> setState { it.copy(loading = false) }
            is ForgetPasswordViewActions.ResetPassword -> handleResetPassword()
            is ForgetPasswordViewActions.SetNewPassword -> {
                setState {
                    it.copy(
                        password = action.newPassword
                    )
                }
            }

            is ForgetPasswordViewActions.SetReNewPassword -> {
                setState {
                    it.copy(
                        rePassword = action.renewPassword
                    )
                }
            }

            is ForgetPasswordViewActions.SetMobile -> {
                setState {
                    it.copy(
                        mobile = action.mobile
                    )
                }
            }

            is ForgetPasswordViewActions.SetNationalId -> {
                setState {
                    it.copy(
                        nationalId = action.nationalId
                    )
                }
            }
        }
    }

    private fun handleResetPassword() {
        viewModelScope.launch {
            newPasswordUseCase.invoke(
                NewPasswordParams(
                    mobileNo = viewState.value.mobile ?: "",
                    nationalCode = viewState.value.nationalId ?: "",
                    newPassword = viewState.value.password ?: "",
                    reNewPassword = viewState.value.rePassword ?: ""
                )
            ).collectLatest { result ->
                when (result) {
                    is Result.Loading -> {
                        setState {
                            it.copy(
                                loading = true
                            )
                        }
                    }

                    is Result.Error -> {
                        setState {
                            it.copy(
                                loading = false,
                                alertModelState = AlertModelState.Dialog(
                                    title = "خطا",
                                    description = " ${result.message}",
                                    positiveButtonTitle = "تایید",
                                    onPositiveClick = {
                                        setState { state -> state.copy(alertModelState = null) }
                                    }
                                )
                            )
                        }
                    }

                    is Result.Success -> {
                        setState {
                            it.copy(
                                loading = false,
                                nationalIdSerial = if (!result.data.nationalCardSerial.isNullOrEmpty()) "${result.data.nationalCardSeri}${result.data.nationalCardSerial}" else null
                            )
                        }
                        postEvent(ForgetPasswordViewEvents.ResetPasswordSuccess)
                    }
                }

            }
        }
    }
}