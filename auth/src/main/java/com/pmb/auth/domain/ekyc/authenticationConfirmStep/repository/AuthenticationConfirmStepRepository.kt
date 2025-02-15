package com.pmb.auth.domain.ekyc.authenticationConfirmStep.repository

import com.pmb.auth.domain.ekyc.authenticationConfirmStep.entity.AuthenticationStepConfirmEntity
import com.pmb.core.platform.Result
import kotlinx.coroutines.flow.Flow

interface AuthenticationConfirmStepRepository {
    fun getAuthenticationStepConfirmData(): Flow<Result<AuthenticationStepConfirmEntity>>
}