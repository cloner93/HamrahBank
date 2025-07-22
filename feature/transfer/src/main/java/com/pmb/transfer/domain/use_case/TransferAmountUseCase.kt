package com.pmb.transfer.domain.use_case

import com.pmb.core.platform.BaseUseCase
import com.pmb.core.platform.Result
import com.pmb.transfer.domain.entity.TransferMethodEntity
import com.pmb.transfer.domain.repository.TransferRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TransferAmountUseCase @Inject constructor(
    private val repository: TransferRepository
) : BaseUseCase<TransferAmountUseCase.Params, List<TransferMethodEntity>>() {
    override suspend fun execute(params: Params): Flow<Result<List<TransferMethodEntity>>>
    = repository.transferAmount(params)


    data class Params(
        val amount: Double,
        val destination: String
    )
}