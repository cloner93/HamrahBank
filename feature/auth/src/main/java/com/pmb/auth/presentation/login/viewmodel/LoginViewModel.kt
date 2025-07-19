package com.pmb.auth.presentation.login.viewmodel

import androidx.lifecycle.viewModelScope
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.Result
import com.pmb.domain.usecae.auth.GetUserDataUseCase
import com.pmb.domain.usecae.auth.RequestLoginParams
import com.pmb.domain.usecae.auth.RequestLoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    initialState: LoginViewState, private val loginRequestLoginUseCase: RequestLoginUseCase,
    private val getUserDataUseCase: GetUserDataUseCase
) : BaseViewModel<LoginViewActions, LoginViewState, LoginViewEvents>(initialState) {
    init {
        handle(LoginViewActions.GetDataStore)
    }

    override fun handle(action: LoginViewActions) {
        when (action) {
            is LoginViewActions.Login -> handleLogin(action)
            LoginViewActions.ClearAlert -> setState { it.copy(alert = null) }
            is LoginViewActions.GetDataStore -> {
                handleGetUserData()
            }
        }
    }

    private fun handleGetUserData() {
        viewModelScope.launch {
            getUserDataUseCase.invoke(Unit).collect{ result->
                when(result) {
                    is Result.Success -> {
                        setState { it.copy(userData = result.data) }
                    }
                    else->{

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
                    password = action.password
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