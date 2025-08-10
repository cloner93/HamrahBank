package com.pmb.domain.usecae.deposit

import com.pmb.core.platform.BaseUseCase
import com.pmb.core.platform.Result
import com.pmb.domain.model.BalanceModel
import com.pmb.domain.repository.deposit.DepositsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BalanceDepositUseCase @Inject constructor(
    private val depositsRepository: DepositsRepository
) : BaseUseCase<BalanceParams, BalanceModel>() {
    override suspend fun execute(params: BalanceParams): Flow<Result<BalanceModel>> {
        return depositsRepository.getBalanceOfDeposit(params.category, params.accountId)
    }
}

data class BalanceParams(val category: Long, val accountId: Long)