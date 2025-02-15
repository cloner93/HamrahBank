package com.pmb.auth.domain.ekyc.authenticationConfirmStep.useCase

import com.pmb.auth.domain.ekyc.authenticationConfirmStep.entity.AuthenticationStepConfirmEntity
import com.pmb.auth.domain.ekyc.authenticationConfirmStep.repository.AuthenticationConfirmStepRepository
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