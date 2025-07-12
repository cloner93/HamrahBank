package com.pmb.auth.domain.register.authentication_information.useCase

import com.pmb.auth.domain.register.authentication_information.entity.GetAuthenticationEntity
import com.pmb.auth.domain.register.authentication_information.repository.AuthenticationInformationRepository
import com.pmb.core.platform.BaseUseCase
import com.pmb.core.platform.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAuthenticationInformationUseCase @Inject constructor(
    private val authenticationInformationRepository: AuthenticationInformationRepository
) : BaseUseCase<Unit, GetAuthenticationEntity>() {
    override suspend fun execute(params: Unit): Flow<Result<GetAuthenticationEntity>> =
        authenticationInformationRepository.getAuthenticationInformation()
}