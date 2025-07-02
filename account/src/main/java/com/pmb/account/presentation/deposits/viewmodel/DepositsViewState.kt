package com.pmb.account.presentation.deposits.viewmodel

import com.pmb.account.presentation.component.TransactionModel
import com.pmb.core.platform.BaseViewState
import com.pmb.domain.model.DepositModel


data class DepositsViewState(
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val errorMessage: String? = null,
    val deposits: List<DepositModel> = emptyList(),
    val selectedDeposit: DepositModel? = null,
    val totalBalance: Double = 0.0,
    val isAmountVisible: Boolean = true,
    val transactions: List<TransactionModel> = emptyList(),
    val showShareDepositInfoBottomSheet: Boolean = false,
    val showMoreBottomSheet: Boolean = false,
    val showDepositListBottomSheet: Boolean = false,
) : BaseViewState