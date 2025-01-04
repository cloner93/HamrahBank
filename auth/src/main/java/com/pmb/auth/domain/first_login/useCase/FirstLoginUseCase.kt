package com.pmb.auth.domain.first_login.useCase

import com.pmb.auth.domain.first_login.entity.FirstLoginStepRequest
import com.pmb.auth.domain.first_login.entity.FirstLoginStepResponse
import com.pmb.auth.domain.first_login.repository.FirstLoginRepository
import com.pmb.core.platform.BaseUseCase
import com.pmb.core.platform.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FirstLoginUseCase @Inject constructor(
    private val firstLoginRepository: FirstLoginRepository
) : BaseUseCase<FirstLoginStepRequest, FirstLoginStepResponse>() {
    override suspend fun execute(params: FirstLoginStepRequest): Flow<Result<FirstLoginStepResponse>> {
        return firstLoginRepository.firstLoginStepConfirmation(params)
    }
}