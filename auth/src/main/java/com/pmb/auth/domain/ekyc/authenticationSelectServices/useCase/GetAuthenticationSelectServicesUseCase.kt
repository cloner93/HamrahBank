package com.pmb.auth.domain.ekyc.authenticationSelectServices.useCase

import com.pmb.auth.domain.ekyc.authenticationSelectServices.entity.SelectServicesEntity
import com.pmb.auth.domain.ekyc.authenticationSelectServices.repository.AuthenticationSelectServicesRepository
import com.pmb.core.platform.BaseUseCase
import com.pmb.core.platform.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAuthenticationSelectServicesUseCase @Inject constructor(
    private val authenticationSelectServicesRepository: AuthenticationSelectServicesRepository
) : BaseUseCase<Unit, SelectServicesEntity>() {
    override suspend fun execute(params: Unit): Flow<Result<SelectServicesEntity>> {
        return authenticationSelectServicesRepository.getServices()
    }
}