package com.pmb.auth.domain.register.deposit_information.useCase

import com.pmb.auth.domain.register.deposit_information.entity.SendDepositInformationEntity
import com.pmb.auth.domain.register.deposit_information.entity.SendDepositInformationParams
import com.pmb.auth.domain.register.deposit_information.repository.DepositInformationRepository
import com.pmb.core.platform.BaseUseCase
import com.pmb.core.platform.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SendDepositInformationUseCase @Inject constructor(
    private val depositInformationRepository: DepositInformationRepository
) : BaseUseCase<SendDepositInformationParams, SendDepositInformationEntity>() {
    override suspend fun execute(params: SendDepositInformationParams): Flow<Result<SendDepositInformationEntity>> =
        depositInformationRepository.sendDepositInformation(params)

}