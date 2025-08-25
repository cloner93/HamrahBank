package com.pmb.auth.domain.ekyc.authentication_select_services.repository

import com.pmb.auth.domain.ekyc.authentication_select_services.entity.ConfirmSelectServicesEntity
import com.pmb.auth.domain.ekyc.authentication_select_services.entity.SelectServicesEntity
import com.pmb.auth.domain.ekyc.authentication_select_services.entity.SelectServicesParams
import com.pmb.core.platform.Result
import kotlinx.coroutines.flow.Flow

interface AuthenticationSelectServicesRepository {
    fun getServices(): Flow<Result<SelectServicesEntity>>
    fun confirmServices(params: SelectServicesParams): Flow<Result<ConfirmSelectServicesEntity>>
}