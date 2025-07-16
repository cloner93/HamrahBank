package com.pmb.domain.usecae.deposit

import com.pmb.core.platform.BaseUseCase
import com.pmb.core.platform.Result
import com.pmb.domain.model.DepositModel
import com.pmb.domain.repository.DepositsRepository
import com.pmb.domain.repository.auth.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserDepositListUseCase @Inject constructor(
    private val depositsRepository: DepositsRepository
) : BaseUseCase<Unit, List<DepositModel>>() {
    override suspend fun execute(params: Unit): Flow<Result<List<DepositModel>>> {
        return depositsRepository.getDepositList()
    }
}