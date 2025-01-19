package com.pmb.auth.domain.first_login.repository

import com.pmb.auth.domain.first_login.entity.FirstLoginStepRequest
import com.pmb.auth.domain.first_login.entity.FirstLoginStepResponse
import com.pmb.core.platform.Result
import kotlinx.coroutines.flow.Flow

interface FirstLoginRepository {
    suspend fun firstLoginStepConfirmation(firstLoginStepRequest: FirstLoginStepRequest): Flow<Result<FirstLoginStepResponse>>
}