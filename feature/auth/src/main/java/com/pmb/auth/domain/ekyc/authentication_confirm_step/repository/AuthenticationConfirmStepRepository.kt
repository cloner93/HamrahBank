package com.pmb.auth.domain.ekyc.authentication_confirm_step.repository

import com.pmb.auth.domain.ekyc.authentication_confirm_step.entity.AuthenticationStepConfirmEntity
import com.pmb.core.platform.Result
import kotlinx.coroutines.flow.Flow

interface AuthenticationConfirmStepRepository {
    fun getAuthenticationStepConfirmData(): Flow<Result<AuthenticationStepConfirmEntity>>
}