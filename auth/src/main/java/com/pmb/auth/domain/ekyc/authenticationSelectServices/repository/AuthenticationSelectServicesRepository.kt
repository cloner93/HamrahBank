package com.pmb.auth.domain.ekyc.authenticationSelectServices.repository

import com.pmb.auth.domain.ekyc.authenticationSelectServices.entity.ConfirmSelectServicesEntity
import com.pmb.auth.domain.ekyc.authenticationSelectServices.entity.SelectServicesEntity
import com.pmb.auth.domain.ekyc.authenticationSelectServices.entity.SelectServicesParams
import com.pmb.core.platform.Result
import kotlinx.coroutines.flow.Flow

interface AuthenticationSelectServicesRepository {
    fun getServices(): Flow<Result<SelectServicesEntity>>
    fun confirmServices(params: SelectServicesParams): Flow<Result<ConfirmSelectServicesEntity>>
}