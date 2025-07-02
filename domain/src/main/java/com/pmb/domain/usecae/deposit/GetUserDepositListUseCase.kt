package com.pmb.domain.usecae.deposit

import com.pmb.core.platform.BaseUseCase
import com.pmb.core.platform.Result
import com.pmb.domain.model.DepositModel
import com.pmb.domain.repository.DepositRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserDepositListUseCase @Inject constructor(
    private val cardLIstRepository: DepositRepository
) : BaseUseCase<Unit, List<DepositModel>>() {
    override suspend fun execute(params: Unit): Flow<Result<List<DepositModel>>> {
        return cardLIstRepository.getDepositList()
    }
}