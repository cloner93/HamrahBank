package com.pmb.auth.presentaion.login.viewmodel

import androidx.lifecycle.viewModelScope
import com.pmb.auth.domain.login.usecase.RequestLoginParams
import com.pmb.auth.domain.login.usecase.RequestLoginUseCase
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    initialState: LoginViewState, private val loginRequestLoginUseCase: RequestLoginUseCase
) : BaseViewModel<LoginViewActions, LoginViewState, LoginViewEvents>(initialState) {


    override fun handle(action: LoginViewActions) {
        when (action) {
            is LoginViewActions.Login -> handleLogin(action)
            LoginViewActions.ClearAlert -> setState { it.copy(alert = null) }
        }
    }

    private fun handleLogin(action: LoginViewActions.Login) {
        viewModelScope.launch {
            loginRequestLoginUseCase.invoke(
                RequestLoginParams(
                    username = action.username, password = action.password
                )
            ).collect { result ->
                when (result) {
                    is Result.Error -> {
                        setState {
                            it.copy(
                                loading = false,
                                alert = AlertModelState.SnackBar(message = result.message)
                            )
                        }
                    }

                    Result.Loading -> setState { it.copy(loading = true) }
                    is Result.Success -> {
                        setState { it.copy(loading = false) }
                        postEvent(LoginViewEvents.LoginSuccess)
                    }
                }
            }

        }
    }
}