package com.pmb.auth.domain.register.job_information.repository

import com.pmb.auth.domain.register.job_information.entity.JobInformationEntity
import com.pmb.auth.domain.register.job_information.entity.JobInformationParam
import com.pmb.core.platform.Result
import kotlinx.coroutines.flow.Flow

interface JobInformationRepository {
    fun getAnnualIncomePrediction(): Flow<Result<JobInformationEntity>>
    fun sendJobInformation(jobInformationEntity: JobInformationParam): Flow<Result<Boolean>>
}