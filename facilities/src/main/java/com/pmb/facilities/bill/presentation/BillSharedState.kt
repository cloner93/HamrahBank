package com.pmb.facilities.bill.presentation

import com.pmb.core.platform.BaseSharedState
import com.pmb.facilities.bill.domain.bill.entity.BillType

data class BillSharedState(
    val billIdentifier: String = "",
    val billType: BillType ?=null
) : BaseSharedState
