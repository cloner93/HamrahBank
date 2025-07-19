package com.pmb.transfer.domain.use_case

import com.pmb.core.platform.BaseUseCase
import com.pmb.core.platform.Result
import com.pmb.transfer.domain.entity.CardVerificationEntity
import com.pmb.transfer.domain.repository.TransferRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TransferResendVerifyCardInfoUseCase @Inject constructor(
    private val transferRepository: TransferRepository
) : BaseUseCase<TransferResendVerifyCardInfoUseCase.Params, CardVerificationEntity>() {

    override suspend fun execute(params: Params): Flow<Result<CardVerificationEntity>> {
        return transferRepository.transferResendVerifyCardInfo(params)
    }

    data class Params(val id: Long)
}