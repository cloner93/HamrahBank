package com.pmb.transfer.domain.use_case

import com.pmb.core.platform.BaseUseCase
import com.pmb.core.platform.Result
import com.pmb.transfer.domain.entity.TransferMethodEntity
import com.pmb.transfer.domain.repository.TransferRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TransferMethodUseCase @Inject constructor(
    private val transferRepository: TransferRepository
) :
    BaseUseCase<Unit, List<TransferMethodEntity>>() {
    override suspend fun execute(params: Unit): Flow<Result<List<TransferMethodEntity>>> {
        return transferRepository.fetchTransferMethods()
    }
}