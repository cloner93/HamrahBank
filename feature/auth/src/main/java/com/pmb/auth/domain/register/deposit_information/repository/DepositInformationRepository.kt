package com.pmb.auth.domain.register.deposit_information.repository

import com.pmb.auth.domain.register.deposit_information.entity.BranchCityEntity
import com.pmb.auth.domain.register.deposit_information.entity.BranchCityParams
import com.pmb.auth.domain.register.deposit_information.entity.DepositInformationEntity
import com.pmb.auth.domain.register.deposit_information.entity.SendDepositInformationEntity
import com.pmb.auth.domain.register.deposit_information.entity.SendDepositInformationParams
import com.pmb.core.platform.Result
import kotlinx.coroutines.flow.Flow

interface DepositInformationRepository {
    suspend fun getDepositInformation(): Flow<Result<DepositInformationEntity>>
    suspend fun getBranchCity(params: BranchCityParams): Flow<Result<BranchCityEntity>>
    suspend fun sendDepositInformation(params: SendDepositInformationParams): Flow<Result<SendDepositInformationEntity>>
}