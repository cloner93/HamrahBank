package com.pmb.domain.usecae.deposit

import com.pmb.core.platform.BaseUseCase
import com.pmb.core.platform.Result
import com.pmb.domain.model.DepositModel
import com.pmb.domain.repository.deposit.DepositsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SetDefaultDepositUseCase @Inject constructor(
    private val depositsRepository: DepositsRepository
) : BaseUseCase<DepositModel, Boolean>() {
    override suspend fun execute(params: DepositModel): Flow<Result<Boolean>> {
        return depositsRepository.setDefaultDeposit(params)
    }
}