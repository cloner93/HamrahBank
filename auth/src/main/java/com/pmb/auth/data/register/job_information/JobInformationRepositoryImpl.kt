package com.pmb.auth.data.register.job_information

import com.pmb.auth.domain.register.job_information.entity.AnnualIncomingPrediction
import com.pmb.auth.domain.register.job_information.entity.JobInformationEntity
import com.pmb.auth.domain.register.job_information.entity.JobInformationParam
import com.pmb.auth.domain.register.job_information.repository.JobInformationRepository
import com.pmb.core.platform.Result
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class JobInformationRepositoryImpl @Inject constructor() : JobInformationRepository {
    override fun getAnnualIncomePrediction(): Flow<Result<JobInformationEntity>> = flow {
        emit(Result.Loading)
        val annualIncomingPrediction = listOf(
            AnnualIncomingPrediction(id = 0, income = "10000"),
            AnnualIncomingPrediction(id = 1, income = "200000000"),
            AnnualIncomingPrediction(id = 2, income = "5000000000")
        )
        delay(2000)
        emit(
            Result.Success(
                JobInformationEntity(
                    isSuccess = true,
                    annualIncomingPrediction = annualIncomingPrediction
                )
            )
        )
    }

    override fun sendJobInformation(jobInformationEntity: JobInformationParam): Flow<Result<Boolean>> =flow{
        emit(Result.Loading)
        delay(2000)
        emit(Result.Success(true))
    }
}