package com.pmb.auth.presentation.register.authentication_select_services.viewModel

import com.pmb.auth.domain.ekyc.authentication_select_services.entity.SelectServicesEntity
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewState
import com.pmb.domain.model.openAccount.comissionFee.Income

data class AuthenticationSelectServicesViewState(
    val loading: Boolean = false,
    val alertModelState: AlertModelState? = null,
    val data: ArrayList<Income>? = emptyList<Income>().toCollection(ArrayList()),
    val localData: SelectServicesEntity? = null,
    val totalPrice : String ?=null
) : BaseViewState