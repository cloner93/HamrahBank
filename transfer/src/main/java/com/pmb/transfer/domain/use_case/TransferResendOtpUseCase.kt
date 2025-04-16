package com.pmb.transfer.domain.use_case

import com.pmb.core.platform.BaseUseCase
import com.pmb.core.platform.Result
import com.pmb.transfer.domain.entity.TransferConfirmEntity
import com.pmb.transfer.domain.repository.TransferRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TransferResendOtpUseCase @Inject constructor(
    private val transferRepository: TransferRepository
) : BaseUseCase<TransferResendOtpUseCase.Params, TransferConfirmEntity>() {

    override suspend fun execute(params: Params): Flow<Result<TransferConfirmEntity>> {
        return transferRepository.transferResendOtp(params)
    }

    data class Params(val id: String)
}