package com.pmb.auth.domain.ekyc.authenticationSelectServices.useCase

import com.pmb.auth.domain.ekyc.authenticationSelectServices.entity.ConfirmSelectServicesEntity
import com.pmb.auth.domain.ekyc.authenticationSelectServices.entity.SelectServicesParams
import com.pmb.auth.domain.ekyc.authenticationSelectServices.repository.AuthenticationSelectServicesRepository
import com.pmb.core.platform.BaseUseCase
import com.pmb.core.platform.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ConfirmAuthenticationSelectServicesUseCase @Inject constructor(
    private val authenticationSelectServicesRepository: AuthenticationSelectServicesRepository
) : BaseUseCase<SelectServicesParams, ConfirmSelectServicesEntity>() {
    override suspend fun execute(params: SelectServicesParams): Flow<Result<ConfirmSelectServicesEntity>> {
        return authenticationSelectServicesRepository.confirmServices(params)
    }
}