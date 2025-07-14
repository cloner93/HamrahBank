package com.pmb.account.presentation.deposits.viewmodel

import com.pmb.core.platform.BaseViewAction
import com.pmb.domain.model.DepositModel
import com.pmb.domain.model.TransactionModel

sealed interface DepositsViewActions : BaseViewAction {
    object ShowHelp : DepositsViewActions
    object NavigateToBalanceScreen : DepositsViewActions

    object ShowShareBottomSheet : DepositsViewActions
    class CloseShareBottomSheet(str: String?) : DepositsViewActions

    object NavigateToTransactionScreen : DepositsViewActions
    class NavigateToTransactionDetailScreen(val transaction: TransactionModel) : DepositsViewActions

    object ShowDepositMoreActionBottomSheet : DepositsViewActions
    object CloseDepositMoreActionBottomSheet : DepositsViewActions

    object SetAmountVisibility : DepositsViewActions

    object ShowDepositListBottomSheet : DepositsViewActions
    class CloseDepositListBottomSheet(val model: DepositModel?) : DepositsViewActions
}