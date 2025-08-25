package com.pmb.auth.domain.register.select_job_information.useCase

import com.pmb.auth.domain.register.select_job_information.entity.SelectJobInformationEntity
import com.pmb.auth.domain.register.select_job_information.repository.SelectJobInformationRepository
import com.pmb.core.platform.BaseUseCase
import com.pmb.core.platform.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetJobInformationUseCase @Inject constructor(
    private val selectJobInformationRepository: SelectJobInformationRepository
) : BaseUseCase<Unit, SelectJobInformationEntity>() {
    override suspend fun execute(params: Unit): Flow<Result<SelectJobInformationEntity>> =
        selectJobInformationRepository.getJobInformation()
}