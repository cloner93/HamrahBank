package com.pmb.auth.data.register.authentication_information

import com.pmb.auth.domain.register.authentication_information.entity.City
import com.pmb.auth.domain.register.authentication_information.entity.Education
import com.pmb.auth.domain.register.authentication_information.entity.GetAuthenticationEntity
import com.pmb.auth.domain.register.authentication_information.entity.SendAuthenticationInformationParam
import com.pmb.auth.domain.register.authentication_information.repository.AuthenticationInformationRepository
import com.pmb.core.platform.Result
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthenticationInformationRepositoryImpl @Inject constructor() :
    AuthenticationInformationRepository {
    override suspend fun sendAuthenticationInformation(authenticationInformationParam: SendAuthenticationInformationParam): Flow<Result<Boolean>> =
        flow {
            emit(Result.Loading)
            emit(Result.Success(true))
        }

}