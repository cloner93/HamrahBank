package com.pmb.auth.domain.ekyc.authentication_confirm_step.useCase

import com.pmb.auth.domain.ekyc.authentication_confirm_step.entity.AuthenticationStepConfirmEntity
import com.pmb.auth.domain.ekyc.authentication_confirm_step.repository.AuthenticationConfirmStepRepository
import com.pmb.core.platform.BaseUseCase
import com.pmb.core.platform.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthenticationConfirmStepUseCase @Inject constructor(
    private val authenticationConfirmStepRepository: AuthenticationConfirmStepRepository
) : BaseUseCase<Unit, AuthenticationStepConfirmEntity>() {
    override suspend fun execute(params: Unit): Flow<Result<AuthenticationStepConfirmEntity>> {
        return authenticationConfirmStepRepository.getAuthenticationStepConfirmData()
    }
}