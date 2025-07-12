package com.pmb.auth.domain.register.job_information.useCase

import com.pmb.auth.domain.register.job_information.entity.JobInformationParam
import com.pmb.auth.domain.register.job_information.repository.JobInformationRepository
import com.pmb.core.platform.BaseUseCase
import com.pmb.core.platform.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SendJobInformationUseCase @Inject constructor(
    private val jobInformationRepository: JobInformationRepository
) : BaseUseCase<JobInformationParam, Boolean>() {
    override suspend fun execute(params: JobInformationParam): Flow<Result<Boolean>> =
        jobInformationRepository.sendJobInformation(params)
}