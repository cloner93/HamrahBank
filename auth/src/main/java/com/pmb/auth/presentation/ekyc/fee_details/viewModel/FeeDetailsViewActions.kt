package com.pmb.auth.presentation.ekyc.fee_details.viewModel

import com.pmb.core.platform.BaseViewAction

sealed interface FeeDetailsViewActions : BaseViewAction {
    data object ClearAlert : FeeDetailsViewActions
    data object LoadFeeDetails : FeeDetailsViewActions
}