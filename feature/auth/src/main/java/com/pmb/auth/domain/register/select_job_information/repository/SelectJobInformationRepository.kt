package com.pmb.auth.domain.register.select_job_information.repository

import com.pmb.auth.domain.register.select_job_information.entity.SelectJobInformationEntity
import com.pmb.core.platform.Result
import kotlinx.coroutines.flow.Flow

interface SelectJobInformationRepository {
    suspend fun getJobInformation(): Flow<Result<SelectJobInformationEntity>>
}