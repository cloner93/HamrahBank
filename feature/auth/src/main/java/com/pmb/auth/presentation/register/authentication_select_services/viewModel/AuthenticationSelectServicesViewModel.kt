package com.pmb.auth.presentation.register.authentication_select_services.viewModel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.pmb.auth.domain.ekyc.authentication_select_services.useCase.ConfirmAuthenticationSelectServicesUseCase
import com.pmb.auth.domain.ekyc.authentication_select_services.useCase.GetAuthenticationSelectServicesUseCase
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewModel
import com.pmb.core.platform.Result
import com.pmb.core.utils.toCurrency
import com.pmb.domain.model.openAccount.comissionFee.CommissionFee
import com.pmb.domain.usecae.auth.openAccount.FetchCommissionFeeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthenticationSelectServicesViewModel @Inject constructor(
    initialState: AuthenticationSelectServicesViewState,
    private val confirmAuthenticationSelectServicesUseCase: ConfirmAuthenticationSelectServicesUseCase,
    private val fetchCommissionFeeUseCase: FetchCommissionFeeUseCase,
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
                handleGetLocalSelectServicesData()
                handleGetSelectServicesData()
            }

        }

    }

    private fun handleGetLocalSelectServicesData() {
        viewModelScope.launch {
            getAuthenticationSelectServicesUseCase.invoke(Unit).collect { result ->
                when (result) {
                    is Result.Success -> {
                        setState {
                            it.copy(
                                loading = false,
                                localData = result.data
                            )
                        }
                    }

                    else -> {

                    }
                }
            }
        }
    }

    private fun handleGetSelectServicesData() {
        viewModelScope.launch {
            fetchCommissionFeeUseCase.invoke(Unit).collect { result ->
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
                                data = result.data.commissionFeeInfo.toCollection(ArrayList()),
                                totalPrice = viewState.value.data?.sumOf { it.amount }?.toDouble()
                                    ?.toCurrency()
                            )
                        }
                    }
                }
            }
        }
    }

    fun getIsCheckedFlagIds() =
        viewState.value.localData?.selectServicesList?.filter { it.isChecked.value }?.map { it.id }

    fun hasIsCheckedFlag() =
        viewState.value.localData?.selectServicesList?.filter { it.isChecked.value }

    fun changeSelectServicesFlag(id: Int) {
        viewState.value.localData?.selectServicesList?.findLast { it.id == id }?.id?.let {
            viewState.value.localData?.selectServicesList?.get(it)?.apply selectServicesList@{
                isChecked.value = isChecked.value == false
                if (isChecked.value) {
                    viewState.value.data?.apply {
                        this.add(
                            CommissionFee(
                                desc = this@selectServicesList.title,
                                amount = this@selectServicesList.price.toLong(),
                                code = id,
                                optional = true
                            )
                        )
                    }

                } else {
                    viewState.value.data?.apply {
                        this.remove(
                            CommissionFee(
                                desc = this@selectServicesList.title,
                                amount = this@selectServicesList.price.toLong(),
                                code = id,
                                optional = true
                            )
                        )
                    }
                }
            }
        }
        setState {
            it.copy(
                totalPrice = viewState.value.data?.sumOf { it.amount }?.toDouble()?.toCurrency()
            )
        }
        Log.d("lists", viewState.value.data.toString())
    }


    init {
        handle(AuthenticationSelectServicesViewActions.LoadSelectedServicesData)
    }
}



