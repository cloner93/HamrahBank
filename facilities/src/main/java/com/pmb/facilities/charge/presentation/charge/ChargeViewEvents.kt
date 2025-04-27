package com.pmb.facilities.charge.presentation.charge

import com.pmb.core.platform.BaseViewEvent

sealed interface ChargeViewEvents : BaseViewEvent{
    data object UseTheLatestNumber: ChargeViewEvents
}