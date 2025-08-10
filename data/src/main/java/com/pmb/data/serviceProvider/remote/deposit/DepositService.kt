package com.pmb.data.serviceProvider.remote.deposit

import com.pmb.core.platform.Result
import com.pmb.domain.model.BalanceModel
import com.pmb.domain.model.BalanceRequest
import com.pmb.domain.model.Deposit
import com.pmb.model.SuccessData
import kotlinx.coroutines.flow.Flow

interface DepositService {
    fun getDepositList(): Flow<Result<SuccessData<List<Deposit>>>>
    fun getBalanceOfDeposit(balance: BalanceRequest): Flow<Result<SuccessData<BalanceModel>>>
}