package com.pmb.auth.presentation.register.deposit_information.viewModel

import com.pmb.auth.domain.register.deposit_information.entity.SendDepositInformationParams
import com.pmb.auth.domain.register.search_opening_branch.entity.OpeningBranch
import com.pmb.core.platform.BaseViewAction

sealed interface DepositInformationViewActions : BaseViewAction {
    data object ClearAlert : DepositInformationViewActions
    data object GetDepositInformation : DepositInformationViewActions
    data class GetBranchCity(val provinceId: Int) : DepositInformationViewActions
    data class SetCityId(val cityId: Int) :
        DepositInformationViewActions

    data class SendDepositInformation(
        val sendDepositInformationParams: SendDepositInformationParams,
    ) : DepositInformationViewActions

    data class DepositType(val depositType: Int) : DepositInformationViewActions
    data class SetOpeningBranch(val openingBranchId: OpeningBranch) : DepositInformationViewActions
    data object SelectRules : DepositInformationViewActions
//    data class AnnualIncomingPrediction(val annualIncomingPrediction: Int) :
//        DepositInformationViewActions
}

