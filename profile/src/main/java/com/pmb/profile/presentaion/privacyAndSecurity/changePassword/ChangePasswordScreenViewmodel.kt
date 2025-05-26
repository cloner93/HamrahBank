package com.pmb.profile.presentaion.privacyAndSecurity.changePassword

import com.pmb.core.platform.BaseViewAction
import com.pmb.core.platform.BaseViewEvent
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.BaseViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChangePasswordScreenViewmodel @Inject constructor(
    initialState: ChangePasswordScreenViewState
) :
    BaseViewModel<ChangePasswordScreenViewActions, ChangePasswordScreenViewState, ChangePasswordScreenViewEvents>(
        initialState
    ) {
    override fun handle(action: ChangePasswordScreenViewActions) {
        when (action) {
            is ChangePasswordScreenViewActions.SetOldPassword -> {
                setState {
                    it.copy(
                        oldPassword = action.oldPassword,
                        isOldPasswordValid = action.oldPassword.isNotEmpty()
                    )
                }
                validatePassword()
            }

            is ChangePasswordScreenViewActions.SetNewPassword -> {
                setState {
                    it.copy(
                        newPassword = action.newPassword
                    )
                }
            }

            is ChangePasswordScreenViewActions.SetNewPasswordValid -> {
                setState {
                    it.copy(
                        isNewPasswordValid = action.isNewPasswordValid
                    )
                }
                validatePassword()
            }

            is ChangePasswordScreenViewActions.SetRenewPassword -> {
                setState {
                    it.copy(
                        renewPassword = action.renewPassword,
                        isRenewPasswordValid = action.renewPassword.isNotEmpty() &&
                                viewState.value.newPassword == action.renewPassword
                    )
                }
                validatePassword()
            }

        }
    }


    private fun validatePassword() {

        val isAllPasswordOk =
            viewState.value.isOldPasswordValid &&
                    viewState.value.isNewPasswordValid &&
                    viewState.value.isRenewPasswordValid

        setState {
            it.copy(
                isAllPasswordOk = isAllPasswordOk
            )
        }

    }
}

sealed interface ChangePasswordScreenViewEvents : BaseViewEvent {
    data class ShowError(val message: String) : ChangePasswordScreenViewEvents
    object NavigateBack : ChangePasswordScreenViewEvents

}

sealed interface ChangePasswordScreenViewActions : BaseViewAction {
    class SetOldPassword(val oldPassword: String) : ChangePasswordScreenViewActions
    class SetNewPassword(val newPassword: String) : ChangePasswordScreenViewActions
    class SetNewPasswordValid(val isNewPasswordValid: Boolean) : ChangePasswordScreenViewActions
    class SetRenewPassword(val renewPassword: String) : ChangePasswordScreenViewActions

}

data class ChangePasswordScreenViewState(
    val isLoading: Boolean = false,
    val oldPassword: String = "",
    val isOldPasswordValid: Boolean = false,
    val newPassword: String = "",
    val isNewPasswordValid: Boolean = false,
    val renewPassword: String = "",
    val isRenewPasswordValid: Boolean = false,

    val isAllPasswordOk: Boolean = false
) : BaseViewState
