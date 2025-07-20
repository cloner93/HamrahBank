package com.pmb.auth.presentation.register.deposit_information.viewModel

import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewState
import com.pmb.domain.model.openAccount.accountType.AccType
import com.pmb.domain.model.openAccount.accountType.FetchAccountTypeResponse
import com.pmb.domain.model.openAccount.accountType.Province
import com.pmb.domain.model.openAccount.branchName.Branch
import com.pmb.domain.model.openAccount.cityName.City

data class DepositInformationViewState(
    val isLoading: Boolean = false,
    val alertModelState: AlertModelState? = null,
    val isChecked: Boolean = false,
    val fetchAccountTypeResponse: FetchAccountTypeResponse? = null,
    val accType: AccType? = null,
    val province: Province? = null,
    val cityList: List<City>? = null,
    val city: City? = null,
    val branch: Branch? = null
) : BaseViewState