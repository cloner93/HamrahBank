package com.pmb.account.presentation.deposits.viewmodel

import com.pmb.account.presentation.component.DepositModel
import com.pmb.core.platform.BaseViewAction

sealed interface DepositsViewActions : BaseViewAction {
    object ShowHelp : DepositsViewActions
    object NavigateToBalanceScreen : DepositsViewActions

    object ShowShareBottomSheet : DepositsViewActions
    class CloseShareBottomSheet(str: String?) : DepositsViewActions

    object NavigateToTransactionScreen : DepositsViewActions
    class NavigateToTransactionDetailScreen(val transactionId: String) : DepositsViewActions

    object ShowDepositMoreActionBottomSheet : DepositsViewActions
    object CloseDepositMoreActionBottomSheet : DepositsViewActions

    object SetAmountVisibility : DepositsViewActions

    object ShowDepositListBottomSheet : DepositsViewActions
    class CloseDepositListBottomSheet(val model: DepositModel?) : DepositsViewActions
}