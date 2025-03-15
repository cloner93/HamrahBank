package com.pmb.auth.presentation.register.deposit_information.viewModel

import com.pmb.auth.domain.register.deposit_information.entity.BranchCityEntity
import com.pmb.auth.domain.register.deposit_information.entity.DepositInformationEntity
import com.pmb.auth.domain.register.deposit_information.entity.SendDepositInformationParams
import com.pmb.auth.domain.register.search_opening_branch.entity.OpeningBranch
import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewState

data class DepositInformationViewState(
    val isLoading: Boolean = false,
    val alertModelState: AlertModelState? = null,
    val depositInformation: DepositInformationEntity? = null,
    val branchCity: BranchCityEntity? = null,
    val openedBranch: OpeningBranch? = null,
    val sendDepositInformationParams: SendDepositInformationParams? = null,
    val isChecked :Boolean =false
) : BaseViewState