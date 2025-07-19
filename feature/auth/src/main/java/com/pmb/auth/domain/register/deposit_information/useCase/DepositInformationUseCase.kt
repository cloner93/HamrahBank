package com.pmb.auth.domain.register.deposit_information.useCase

import com.pmb.auth.domain.register.deposit_information.entity.DepositInformationEntity
import com.pmb.auth.domain.register.deposit_information.repository.DepositInformationRepository
import com.pmb.core.platform.BaseUseCase
import com.pmb.core.platform.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DepositInformationUseCase @Inject constructor(private val depositInformationRepository: DepositInformationRepository) :
    BaseUseCase<Unit, DepositInformationEntity>() {
    override suspend fun execute(params: Unit): Flow<Result<DepositInformationEntity>> =
        depositInformationRepository.getDepositInformation()

}