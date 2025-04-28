package com.pmb.facilities.charge.presentation.charge_history.viewModel

import com.pmb.core.platform.BaseViewAction

sealed interface ChargeHistoryViewActions : BaseViewAction {
    data object ClearAlertModelState : ChargeHistoryViewActions
    data class GetChargeHistory(val filter: String) : ChargeHistoryViewActions
    data object GetFilterData : ChargeHistoryViewActions
    data class SetSelectedFilter(val filter: String) : ChargeHistoryViewActions
}