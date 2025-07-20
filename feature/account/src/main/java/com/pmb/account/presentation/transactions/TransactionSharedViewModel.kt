package com.pmb.account.presentation.transactions

import com.pmb.core.platform.BaseSharedState
import com.pmb.core.platform.BaseSharedViewModel
import com.pmb.domain.model.DepositModel
import com.pmb.domain.model.transaztion.Summarize
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject

@HiltViewModel
class TransactionSharedViewModel @Inject constructor() :
    BaseSharedViewModel<TransactionSharedState>(TransactionSharedState())

data class TransactionSharedState(
    val selectedDeposit: DepositModel? = null,
    val summarize: Summarize? = null
) : BaseSharedState