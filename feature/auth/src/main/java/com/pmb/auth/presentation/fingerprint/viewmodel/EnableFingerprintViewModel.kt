package com.pmb.auth.presentation.fingerprint.viewmodel

import androidx.lifecycle.viewModelScope
import com.pmb.core.platform.BaseViewAction
import com.pmb.core.platform.BaseViewEvent
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.BaseViewState
import com.pmb.domain.usecae.auth.biometric.SetUserBiometricStateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EnableFingerprintViewModel @Inject constructor(
    initialState: EnableFingerprintViewState,
    private val setUserBiometricStateUseCase: SetUserBiometricStateUseCase
) : BaseViewModel<EnableFingerprintViewActions, EnableFingerprintViewState, EnableFingerprintViewEvents>(
    initialState
) {
    override fun handle(action: EnableFingerprintViewActions) {
        when (action) {
            EnableFingerprintViewActions.Enable -> {
                viewModelScope.launch {
                    setUserBiometricStateUseCase.invoke(true).collect()
                }
                postEvent(EnableFingerprintViewEvents.NavigateForward)
            }
        }
    }
}

sealed interface EnableFingerprintViewEvents : BaseViewEvent {
    object NavigateForward : EnableFingerprintViewEvents
}

data class EnableFingerprintViewState(
    val fingerPrintIsEnable: Boolean = false,
) : BaseViewState

sealed interface EnableFingerprintViewActions : BaseViewAction {
    object Enable : EnableFingerprintViewActions
}
