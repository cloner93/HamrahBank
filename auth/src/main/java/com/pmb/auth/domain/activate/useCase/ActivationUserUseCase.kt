package com.pmb.auth.domain.activate.useCase

import com.pmb.auth.domain.activate.entity.ActivateEntity
import com.pmb.auth.domain.activate.entity.ActivateParams
import com.pmb.auth.domain.activate.repository.ActivateRepository
import com.pmb.core.platform.BaseUseCase
import com.pmb.core.platform.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ActivationUserUseCase @Inject constructor(
    private val activateRepository: ActivateRepository
) : BaseUseCase<ActivateParams, ActivateEntity>() {
    override suspend fun execute(params: ActivateParams): Flow<Result<ActivateEntity>> {
        return activateRepository.activeUser(params)
    }
}