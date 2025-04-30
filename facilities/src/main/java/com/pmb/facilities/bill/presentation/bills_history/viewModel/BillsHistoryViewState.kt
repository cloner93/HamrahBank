package com.pmb.facilities.bill.presentation.bills_history.viewModel

import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewState
import com.pmb.facilities.charge.domain.charge.entity.ChargeData
import com.pmb.facilities.charge.domain.charge_history.entity.ChargeHistoryFilterEntity

data class BillsHistoryViewState(
    val isLoading: Boolean = false,
    val alertModelState: AlertModelState? = null,
    val filterItemList: List<ChargeHistoryFilterEntity>? = null,
    val filter:String?=null,
    val dataList: List<ChargeData>? = null
): BaseViewState
