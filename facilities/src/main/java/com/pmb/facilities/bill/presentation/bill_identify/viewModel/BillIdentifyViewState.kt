package com.pmb.facilities.bill.presentation.bill_identify.viewModel

import com.pmb.core.platform.AlertModelState
import com.pmb.core.platform.BaseViewState
import com.pmb.facilities.bill.domain.bill_id.entity.BillIdEntity

data class BillIdentifyViewState(
    val isLoading: Boolean = false,
    val alertModelState: AlertModelState? = null,
    val billIdEntity: BillIdEntity? = null,
    val bottomSheetVisibility: Boolean = false
) : BaseViewState
