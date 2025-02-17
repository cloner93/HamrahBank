package com.pmb.auth.domain.ekyc.authentication_select_services.useCase

import com.pmb.auth.domain.ekyc.authentication_select_services.entity.ConfirmSelectServicesEntity
import com.pmb.auth.domain.ekyc.authentication_select_services.entity.SelectServicesParams
import com.pmb.auth.domain.ekyc.authentication_select_services.repository.AuthenticationSelectServicesRepository
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