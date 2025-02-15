package com.pmb.auth.presentaion.ekyc.feeDetails.viewModel

import com.pmb.core.platform.BaseViewAction

sealed interface FeeDetailsViewActions : BaseViewAction {
    data object ClearAlert : FeeDetailsViewActions
    data object LoadFeeDetails : FeeDetailsViewActions
}