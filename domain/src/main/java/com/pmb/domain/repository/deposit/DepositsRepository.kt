package com.pmb.domain.repository.deposit

import com.pmb.core.platform.Result
import com.pmb.domain.model.BalanceModel
import com.pmb.domain.model.DepositModel
import kotlinx.coroutines.flow.Flow

interface DepositsRepository {
    fun getDepositList(): Flow<Result<List<DepositModel>>>
    fun getDefaultDeposit(): Flow<Result<DepositModel?>>
    fun setDefaultDeposit(depositModel: DepositModel): Flow<Result<Boolean>>
    fun getBalanceOfDeposit(category: Long, accountId: Long): Flow<Result<BalanceModel>>
}
