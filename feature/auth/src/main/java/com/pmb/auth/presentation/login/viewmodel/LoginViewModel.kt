package com.pmb.auth.presentation.login.viewmodel

import androidx.lifecycle.viewModelScope
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.Result
import com.pmb.domain.usecae.auth.GetUserDataUseCase
import com.pmb.domain.usecae.auth.RequestLoginParams
import com.pmb.domain.usecae.auth.RequestLoginUseCase
import com.pmb.domain.usecae.auth.biometric.GetUserBiometricStateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    initialState: LoginViewState,
    private val loginRequestLoginUseCase: RequestLoginUseCase,
    private val getUserDataUseCase: GetUserDataUseCase,
    private val getUserBiometricStateUseCase: GetUserBiometricStateUseCase
) : BaseViewModel<LoginViewActions, LoginViewState, LoginViewEvents>(initialState) {
    init {
        handle(LoginViewActions.GetDataStore)
    }

    private fun checkBimetric() {

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
                                biometricState = biometricState
                            )
                        }
                        postEvent(LoginViewEvents.ShowBiometricPrompt(biometricState))

                    }
                }
            }
        }
    }

    override fun handle(action: LoginViewActions) {
        when (action) {
            is LoginViewActions.Login -> handleLogin(action)
            LoginViewActions.ClearAlert -> setState { it.copy(alert = null) }
            is LoginViewActions.GetDataStore -> {
                handleGetUserData()
            }

            is LoginViewActions.LoginWithFinger -> {
                val state = viewState.value.biometricState

                if (state)
                    checkBimetric()
            }

            is LoginViewActions.ShowError -> {
                setState {
                    it.copy(
                        alert = action.alert
                    )
                }
            }
        }
    }

    private fun handleGetUserData() {
        viewModelScope.launch {
            getUserDataUseCase.invoke(Unit).collect { result ->
                when (result) {
                    is Result.Success -> {
                        setState { it.copy(userData = result.data) }
                        checkBimetric()
                    }

                    else -> {

                    }
                }
            }
        }
    }

    private fun handleLogin(action: LoginViewActions.Login) {
        viewModelScope.launch {
            loginRequestLoginUseCase.invoke(
                RequestLoginParams(
                    customerId = action.mobileNo,
                    username = action.username,
                    password = action.password,
                    useFinger = action.useFinger
                )
            ).collect { result ->
                when (result) {
                    is Result.Error -> {
                        setState {
                            it.copy(
                                isLoading = false, alert = AlertModelState.Dialog(
                                    title = "خطا در ورود به حساب",
                                    description = result.message,
                                    positiveButtonTitle = "okay",
                                    onPositiveClick = {
                                        setState { state -> state.copy(alert = null) }
                                    }
                                )
                            )
                        }
                    }

                    Result.Loading -> {
                        setState { it.copy(isLoading = true) }
                    }

                    is Result.Success<*> -> {
                        setState { it.copy(isLoading = false) }
                        postEvent(LoginViewEvents.LoginSuccess)
                    }
                }

            }

        }
    }
}