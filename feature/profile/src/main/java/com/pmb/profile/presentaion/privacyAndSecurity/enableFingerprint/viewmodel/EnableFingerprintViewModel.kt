package com.pmb.profile.presentaion.privacyAndSecurity.enableFingerprint.viewmodel

import androidx.lifecycle.viewModelScope
import com.pmb.core.platform.BaseViewAction
import com.pmb.core.platform.BaseViewEvent
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.BaseViewState
import com.pmb.core.platform.Result
import com.pmb.domain.model.UserData
import com.pmb.domain.usecae.auth.GetUserDataUseCase
import com.pmb.domain.usecae.auth.biometric.SetUserBiometricStateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EnableFingerprintViewModel @Inject constructor(
    private val getUserDataUseCase: GetUserDataUseCase,
    private val setUserBiometricStateUseCase: SetUserBiometricStateUseCase
) : BaseViewModel<EnableFingerprintViewActions, EnableFingerprintViewState, EnableFingerprintViewEvents>(
    EnableFingerprintViewState()
) {
    init {
        handleGetUserData()
    }

    override fun handle(action: EnableFingerprintViewActions) {
        when (action) {
            is EnableFingerprintViewActions.EnableFingerprint -> {
                viewState.value.userData?.let { userData ->
                    if (userData.password == action.password) {

                        setState {
                            it.copy(
                                passwordStateError = ""
                            )
                        }
                        postEvent(EnableFingerprintViewEvents.EnableFingerprint)
                    } else {
                        setState {
                            it.copy(
                                passwordStateError = "رمزعبور اشتباه است."
                            )
                        }
                    }
                }
            }

            is EnableFingerprintViewActions.PasswordChange -> {
                setState {
                    it.copy(
                        password = action.password
                    )
                }
            }

            EnableFingerprintViewActions.Enable -> {
                viewModelScope.launch {
                    setUserBiometricStateUseCase.invoke(true).collect()
                }
                postEvent(EnableFingerprintViewEvents.NavigateBack)
            }
        }
    }


    private fun handleGetUserData() {
        viewModelScope.launch {
            getUserDataUseCase.invoke(Unit).collect { result ->
                when (result) {
                    is Result.Success -> {
                        setState { it.copy(userData = result.data) }
                    }

                    else -> {

                    }
                }
            }
        }
    }
}

sealed interface EnableFingerprintViewEvents : BaseViewEvent {
    object EnableFingerprint : EnableFingerprintViewEvents
    object NavigateBack : EnableFingerprintViewEvents
}

data class EnableFingerprintViewState(
    val fingerPrintIsEnable: Boolean = false,
    val userData: UserData? = null,
    val password: String = "",
    val passwordStateError: String = ""
) : BaseViewState

sealed interface EnableFingerprintViewActions : BaseViewAction {
    class EnableFingerprint(val password: String) : EnableFingerprintViewActions
    class PasswordChange(val password: String) : EnableFingerprintViewActions
    object Enable : EnableFingerprintViewActions
}
