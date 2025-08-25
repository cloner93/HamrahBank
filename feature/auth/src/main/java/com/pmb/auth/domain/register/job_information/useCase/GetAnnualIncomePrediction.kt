package com.pmb.auth.domain.register.job_information.useCase

import com.pmb.auth.domain.register.job_information.entity.JobInformationEntity
import com.pmb.auth.domain.register.job_information.repository.JobInformationRepository
import com.pmb.core.platform.BaseUseCase
import com.pmb.core.platform.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAnnualIncomePrediction @Inject constructor(
    private val jobInformationRepository: JobInformationRepository
) :BaseUseCase<Unit,JobInformationEntity>(){
    override suspend fun execute(params: Unit): Flow<Result<JobInformationEntity>>  = jobInformationRepository.getAnnualIncomePrediction()
}