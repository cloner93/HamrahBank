package com.pmb.auth.presentaion.ekyc.authenticationSelectServices.viewModel

import androidx.lifecycle.viewModelScope
import com.pmb.auth.domain.ekyc.authenticationSelectServices.entity.SelectServicesParams
import com.pmb.auth.domain.ekyc.authenticationSelectServices.useCase.ConfirmAuthenticationSelectServicesUseCase
import com.pmb.auth.domain.ekyc.authenticationSelectServices.useCase.GetAuthenticationSelectServicesUseCase
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.Result
import com.pmb.core.utils.toCurrency
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthenticationSelectServicesViewModel @Inject constructor(
    initialState: AuthenticationSelectServicesViewState,
    private val confirmAuthenticationSelectServicesUseCase: ConfirmAuthenticationSelectServicesUseCase,
    private val getAuthenticationSelectServicesUseCase: GetAuthenticationSelectServicesUseCase
) : BaseViewModel<AuthenticationSelectServicesViewActions, AuthenticationSelectServicesViewState, AuthenticationSelectServicesViewEvent>(
    initialState
) {

    override fun handle(action: AuthenticationSelectServicesViewActions) {
        when (action) {
            AuthenticationSelectServicesViewActions.ClearAlert -> {
                setState { it.copy(loading = false) }
            }

            AuthenticationSelectServicesViewActions.LoadSelectedServicesData -> {
                handleGetSelectServicesData()
            }

            is AuthenticationSelectServicesViewActions.ConfirmAuthenticationSelectedServices -> {
                handleConfirmAuthenticationSelectedServices(action)
            }
        }

    }

    private fun handleGetSelectServicesData() {
        viewModelScope.launch {
            getAuthenticationSelectServicesUseCase.invoke(Unit).collect { result ->
                when (result) {
                    is Result.Loading -> {
                        setState {
                            it.copy(loading = true)
                        }
                    }

                    is Result.Error -> {
                        setState {
                            it.copy(
                                loading = false,
                                alertModelState = AlertModelState.SnackBar(
                                    message = result.message,
                                    onActionPerformed = {
                                        setState { state -> state.copy(alertModelState = null) }
                                    },
                                    onDismissed = {
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
                                data = result.data
                            )
                        }
                    }
                }
            }
        }
    }

    fun getIsCheckedFlagIds() =
        viewState.value.data?.selectServicesList?.filter { it.isChecked.value }?.map { it.id }

    fun hasIsCheckedFlag() = viewState.value.data?.selectServicesList?.filter { it.isChecked.value }
    fun changeSelectServicesFlag(id: Int) {
        viewState.value.data?.selectServicesList?.findLast { it.id == id }?.id?.let {
            viewState.value.data?.selectServicesList?.get(it)?.apply {
                isChecked.value = isChecked.value == false
            }
        }
    }

    private fun handleConfirmAuthenticationSelectedServices(
        action: AuthenticationSelectServicesViewActions.ConfirmAuthenticationSelectedServices
    ) {
        viewModelScope.launch {
            confirmAuthenticationSelectServicesUseCase.invoke(SelectServicesParams(action.ids))
                .collect { result ->
                    when (result) {
                        is Result.Loading -> {
                            setState {
                                it.copy(
                                    loading = true
                                )
                            }
                        }

                        is Result.Success -> {
                            setState {
                                it.copy(
                                    loading = false,
                                )

                            }
                            postEvent(AuthenticationSelectServicesViewEvent.SelectServicesSucceed)
                        }

                        is Result.Error -> {
                            setState {
                                it.copy(
                                    loading = false,
                                    alertModelState = AlertModelState.SnackBar(
                                        message = result.message,
                                        onActionPerformed = {
                                            setState { state -> state.copy(alertModelState = null) }
                                        },
                                        onDismissed = {
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

    fun getTotalPrice() =
        (viewState.value.data?.selectServicesList?.map { it.price }?.sum() ?: 0.0).toCurrency()

    init {
        handle(AuthenticationSelectServicesViewActions.LoadSelectedServicesData)
    }
}



