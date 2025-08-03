package com.pmb.account.presentation.deposits.viewmodel

import androidx.paging.PagingData
import com.pmb.core.platform.BaseViewState
import com.pmb.domain.model.DepositModel
import com.pmb.domain.model.TransactionModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow


data class DepositsViewState(
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val errorMessage: String? = null,
    val deposits: List<DepositModel> = emptyList(),
    val selectedDeposit: DepositModel? = null,
    val totalBalance: Double = 0.0,
    val isAmountVisible: Boolean = true,
    val transactionFlow: Flow<PagingData<TransactionModel>> = emptyFlow(),
    val transactions: List<TransactionModel> = emptyList(),
    val showShareDepositInfoBottomSheet: Boolean = false,
    val showMoreBottomSheet: Boolean = false,
    val showDepositListBottomSheet: Boolean = false,
    val defaultDepositAccount: DepositModel? = null
) : BaseViewState