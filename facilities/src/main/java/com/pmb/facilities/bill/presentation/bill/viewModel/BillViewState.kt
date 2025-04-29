package com.pmb.facilities.bill.presentation.bill.viewModel

import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewState
import com.pmb.facilities.bill.domain.bill.entity.BillType
import com.pmb.facilities.charge.domain.charge.entity.ChargeData

data class BillViewState(
    val isLoading: Boolean = false,
    val alertModelState: AlertModelState? = null,
    val billsData: List<ChargeData>? = null,
    val billType :BillType ?=null,
    val isBottomSheetVisibility : Boolean = false
) : BaseViewState
