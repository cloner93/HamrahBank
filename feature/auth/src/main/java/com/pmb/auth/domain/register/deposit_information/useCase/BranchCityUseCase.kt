package com.pmb.auth.domain.register.deposit_information.useCase

import com.pmb.auth.domain.register.deposit_information.entity.BranchCityEntity
import com.pmb.auth.domain.register.deposit_information.entity.BranchCityParams
import com.pmb.auth.domain.register.deposit_information.repository.DepositInformationRepository
import com.pmb.core.platform.BaseUseCase
import com.pmb.core.platform.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BranchCityUseCase @Inject constructor(
    private val depositInformationRepository: DepositInformationRepository
) : BaseUseCase<BranchCityParams, BranchCityEntity>() {
    override suspend fun execute(params: BranchCityParams): Flow<Result<BranchCityEntity>> =
        depositInformationRepository.getBranchCity(params)

}