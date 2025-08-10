package com.pmb.account.presentation.deposits.viewmodel

import com.pmb.core.platform.BaseViewAction
import com.pmb.domain.model.DepositModel
import com.pmb.domain.model.TransactionModel

sealed interface DepositsViewActions : BaseViewAction {
    data object ShowHelp : DepositsViewActions
    data object NavigateToBalanceScreen : DepositsViewActions

    data object ShowShareBottomSheet : DepositsViewActions
    class CloseShareBottomSheet(str: String?) : DepositsViewActions

    data object NavigateToTransactionScreen : DepositsViewActions
    class NavigateToTransactionDetailScreen(val transaction: TransactionModel) : DepositsViewActions

    data object ShowDepositMoreActionBottomSheet : DepositsViewActions
    data object CloseDepositMoreActionBottomSheet : DepositsViewActions

    data object SetAmountVisibility : DepositsViewActions

    data object ShowDepositListBottomSheet : DepositsViewActions
    data object RefreshDepositAmount : DepositsViewActions
    data object OpenDepositDetailsScreen : DepositsViewActions
    data class SetDepositAsMain(val model: DepositModel?) : DepositsViewActions

    class CloseDepositListBottomSheet(val model: DepositModel?) : DepositsViewActions

    data object ShowGuideBottomSheet : DepositsViewActions
    data object CloseGuideBottomSheet : DepositsViewActions

}