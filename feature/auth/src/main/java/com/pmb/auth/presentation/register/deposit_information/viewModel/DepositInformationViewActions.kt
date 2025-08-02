package com.pmb.auth.presentation.register.deposit_information.viewModel

import com.pmb.core.platform.BaseViewAction
import com.pmb.domain.model.openAccount.accountType.AccType
import com.pmb.domain.model.openAccount.accountType.Province
import com.pmb.domain.model.openAccount.branchName.Branch
import com.pmb.domain.model.openAccount.cityName.City

sealed interface DepositInformationViewActions : BaseViewAction {
    data object ClearAlert : DepositInformationViewActions


    data class SetOpeningBranch(val openingBranchId: Branch?) : DepositInformationViewActions
    data object SelectRules : DepositInformationViewActions
    data object AcceptRules : DepositInformationViewActions


    data class FetchAccountType(val nationalCode: String, val mobileNo: String) :
        DepositInformationViewActions

    data class SetAccountType(val accType: AccType) : DepositInformationViewActions
    data class SetProvince(val province: Province) : DepositInformationViewActions
    data class SetCity(val city: City?) : DepositInformationViewActions
    data object FetchCommitment : DepositInformationViewActions
}

