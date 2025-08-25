package com.pmb.transfer.domain.use_case

import com.pmb.core.platform.BaseUseCase
import com.pmb.core.platform.Result
import com.pmb.transfer.domain.entity.ReasonEntity
import com.pmb.transfer.domain.repository.TransferRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TransferReasonUseCase @Inject constructor(
    private val transferRepository: TransferRepository
) : BaseUseCase<TransferReasonUseCase.Params, List<ReasonEntity>>() {
    override suspend fun execute(params: Params): Flow<Result<List<ReasonEntity>>> {
        return transferRepository.fetchReasons(params)
    }

    data class Params(
        val transferType: Int,
        val destination: String
    )
}