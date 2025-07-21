package com.pmb.account.presentation.deposits.viewmodel

import com.pmb.core.platform.BaseViewEvent

sealed interface DepositsViewEvents : BaseViewEvent {
    data class ShowToast(val message: String) : DepositsViewEvents
    data class ShowError(val message: String) : DepositsViewEvents
    data class NavigateToTransactionDetails(val transaction: String) : DepositsViewEvents
    object NavigateBack : DepositsViewEvents
    data class DepositSelectionChanged(val depositId: String) : DepositsViewEvents
    object RefreshCompleted : DepositsViewEvents
    object NavigateToTransactionsList : DepositsViewEvents
    object NavigateToBalanceScreen : DepositsViewEvents
}