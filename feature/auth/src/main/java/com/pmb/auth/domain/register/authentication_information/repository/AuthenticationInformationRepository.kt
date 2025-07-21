package com.pmb.auth.domain.register.authentication_information.repository

import com.pmb.auth.domain.register.authentication_information.entity.GetAuthenticationEntity
import com.pmb.auth.domain.register.authentication_information.entity.SendAuthenticationInformationParam
import com.pmb.core.platform.Result
import kotlinx.coroutines.flow.Flow

interface AuthenticationInformationRepository {
    suspend fun sendAuthenticationInformation(authenticationInformationParam: SendAuthenticationInformationParam): Flow<Result<Boolean>>
}