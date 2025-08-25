package com.pmb.domain.usecae.deposit

import com.pmb.core.platform.BaseUseCase
import com.pmb.core.platform.Result
import com.pmb.domain.model.DepositModel
import com.pmb.domain.repository.deposit.DepositsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDefaultDepositUseCase @Inject constructor(
    private val depositsRepository: DepositsRepository
) : BaseUseCase<Unit,DepositModel?>(){
    override suspend fun execute(params: Unit): Flow<Result<DepositModel?>> {
        return depositsRepository.getDefaultDeposit()
    }
}