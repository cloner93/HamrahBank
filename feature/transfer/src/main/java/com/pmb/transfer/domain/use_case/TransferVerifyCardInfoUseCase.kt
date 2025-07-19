package com.pmb.transfer.domain.use_case

import com.pmb.core.platform.BaseUseCase
import com.pmb.core.platform.Result
import com.pmb.transfer.domain.entity.TransferReceiptEntity
import com.pmb.transfer.domain.repository.TransferRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TransferVerifyCardInfoUseCase @Inject constructor(
    private val transferRepository: TransferRepository
): BaseUseCase<TransferVerifyCardInfoUseCase.Params, TransferReceiptEntity>() {
    override suspend fun execute(params: Params): Flow<Result<TransferReceiptEntity>> {
        return transferRepository.transferVerifyCardInfo(params)
    }

    data class Params(val id: String, val cvv2: String, val password: String)
}