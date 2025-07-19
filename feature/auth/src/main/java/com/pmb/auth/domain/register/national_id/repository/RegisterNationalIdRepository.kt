package com.pmb.auth.domain.register.national_id.repository

import com.pmb.auth.domain.register.national_id.entity.RegisterNationalIdEntity
import com.pmb.auth.domain.register.national_id.entity.RegisterNationalIdRequest
import com.pmb.core.platform.Result
import kotlinx.coroutines.flow.Flow

interface RegisterNationalIdRepository {
    suspend fun registerNationalId(params: RegisterNationalIdRequest): Flow<Result<RegisterNationalIdEntity>>
}