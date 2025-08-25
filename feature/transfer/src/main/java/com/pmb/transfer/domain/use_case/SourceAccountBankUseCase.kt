package com.pmb.transfer.domain.use_case

import com.pmb.core.platform.BaseUseCase
import com.pmb.core.platform.Result
import com.pmb.transfer.domain.entity.AccountBankEntity
import com.pmb.transfer.domain.repository.TransferRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SourceAccountBankUseCase @Inject constructor(
    private val transferRepository: TransferRepository
) : BaseUseCase<SourceAccountBankUseCase.Params, List<AccountBankEntity>>() {
    override suspend fun execute(params: Params): Flow<Result<List<AccountBankEntity>>> {
        return transferRepository.fetchSourceAccountBank(params.userId)
    }

    data class Params(val userId: String)
}