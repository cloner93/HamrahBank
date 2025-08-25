package com.pmb.profile.presentaion.privacyAndSecurity.viewmodel

import androidx.lifecycle.viewModelScope
import com.pmb.core.platform.BaseViewAction
import com.pmb.core.platform.BaseViewEvent
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.BaseViewState
import com.pmb.core.platform.Result
import com.pmb.domain.usecae.auth.biometric.GetUserBiometricStateUseCase
import com.pmb.domain.usecae.auth.biometric.SetUserBiometricStateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PrivacySecurityViewModel @Inject constructor(
    private val getUserBiometricStateUseCase: GetUserBiometricStateUseCase,
    private val setUserBiometricStateUseCase: SetUserBiometricStateUseCase
) : BaseViewModel<PrivacySecurityViewActions, PrivacySecurityViewState, PrivacySecurityViewEvents>(
    PrivacySecurityViewState()
) {
    init {
        checkBimetric()
    }

    override fun handle(action: PrivacySecurityViewActions) {
        when (action) {
            is PrivacySecurityViewActions.ShowDiableFingerprintBottomSheet -> {
                setState { it.copy(disableFingerprintBottomSheetState = action.state) }
            }

            PrivacySecurityViewActions.DiableFingerprint -> {
                setState {
                    it.copy(
                        disableFingerprintBottomSheetState = false,
                        fingerPrintIsEnable = false
                    )
                }
                viewModelScope.launch {
                    setUserBiometricStateUseCase.invoke(false).collect()
                }
            }
        }
    }

    fun checkBimetric() {
        viewModelScope.launch {
            getUserBiometricStateUseCase.invoke(Unit).collect { state ->
                when (state) {
                    is Result.Error -> {

                    }

                    Result.Loading -> {

                    }

                    is Result.Success<*> -> {
                        val biometricState = state.data as Boolean
                        setState {
                            it.copy(
                                fingerPrintIsEnable = biometricState
                            )
                        }
                    }
                }
            }
        }
    }
}

sealed interface PrivacySecurityViewEvents : BaseViewEvent

data class PrivacySecurityViewState(
    val fingerPrintIsEnable: Boolean = false,
    val disableFingerprintBottomSheetState: Boolean = false,
) : BaseViewState

sealed interface PrivacySecurityViewActions : BaseViewAction {
    class ShowDiableFingerprintBottomSheet(val state: Boolean) : PrivacySecurityViewActions
    object DiableFingerprint : PrivacySecurityViewActions
}
