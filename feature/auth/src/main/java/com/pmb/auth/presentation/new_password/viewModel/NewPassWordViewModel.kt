package com.pmb.auth.presentation.new_password.viewModel

import androidx.lifecycle.viewModelScope
import com.pmb.auth.domain.new_password.entity.NewPasswordParams
import com.pmb.auth.domain.new_password.useCase.NewPasswordUseCase
import com.pmb.ballon.models.AccountSampleModel
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewPassWordViewModel @Inject constructor(
    initialState: NewPasswordViewState,
    private val newPasswordUseCase: NewPasswordUseCase
) : BaseViewModel<NewPasswordViewActions, NewPasswordViewState, NewPassWordViewEvents>(initialState) {
    private val accountSampleModel = AccountSampleModel()
    fun getAccountSampleModel() = accountSampleModel
    override fun handle(action: NewPasswordViewActions) {
        when (action) {
            NewPasswordViewActions.ClearAlert -> {
                setState { it.copy(loading = false) }
            }

            is NewPasswordViewActions.ChangePassWord -> {
                handleChangePassWord(action)
            }

            NewPasswordViewActions.ClearBottomSheet -> setState { it.copy(isShowBottomSheet = false) }
        }
    }

    private fun handleChangePassWord(action: NewPasswordViewActions.ChangePassWord) {
        viewModelScope.launch {
            newPasswordUseCase.invoke(
                NewPasswordParams(
                    userName = action.userName,
                    mobileNumber = action.mobileNumber,
                    passWord = action.passWord
                )
            ).collect { result ->
                when (result) {
                    is Result.Success -> {
                        setState {
                            it.copy(
                                loading = false,
                                isShowBottomSheet = true
                            )
                        }
                        postEvent(NewPassWordViewEvents.ChangePassWordSucceed)
                    }

                    is Result.Loading -> setState { it.copy(loading = true) }
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
                }

            }
        }
    }
}