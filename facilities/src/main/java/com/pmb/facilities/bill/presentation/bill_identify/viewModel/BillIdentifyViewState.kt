package com.pmb.facilities.bill.presentation.bill_identify.viewModel

import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewState

data class BillIdentifyViewState(
    val isLoading: Boolean = false,
    val alertModelState: AlertModelState?=null
): BaseViewState
