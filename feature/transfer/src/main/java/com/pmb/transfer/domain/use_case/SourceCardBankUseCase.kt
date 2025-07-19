package com.pmb.transfer.domain.use_case

import com.pmb.core.platform.BaseUseCase
import com.pmb.core.platform.Result
import com.pmb.transfer.domain.entity.CardBankEntity
import com.pmb.transfer.domain.repository.TransferRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SourceCardBankUseCase @Inject constructor(
    private val transferRepository: TransferRepository
) : BaseUseCase<SourceCardBankUseCase.Params, List<CardBankEntity>>() {
    override suspend fun execute(params: Params): Flow<Result<List<CardBankEntity>>> {
        return transferRepository.fetchSourceCartBank(params.userId)
    }

    data class Params(val userId: String)
}