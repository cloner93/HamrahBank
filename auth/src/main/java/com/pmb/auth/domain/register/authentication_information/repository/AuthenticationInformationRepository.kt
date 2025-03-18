package com.pmb.auth.domain.register.authentication_information.repository

import com.pmb.auth.domain.register.authentication_information.entity.GetAuthenticationEntity
import com.pmb.auth.domain.register.authentication_information.entity.SendAuthenticationInformationParam
import com.pmb.core.platform.Result
import kotlinx.coroutines.flow.Flow

interface AuthenticationInformationRepository {
    suspend fun getAuthenticationInformation(): Flow<Result<GetAuthenticationEntity>>
    suspend fun sendAuthenticationInformation(authenticationInformationParam: SendAuthenticationInformationParam): Flow<Result<Boolean>>
}