package com.pmb.auth.domain.register.authentication_information.useCase

import com.pmb.auth.domain.register.authentication_information.entity.SendAuthenticationInformationParam
import com.pmb.auth.domain.register.authentication_information.repository.AuthenticationInformationRepository
import com.pmb.core.platform.BaseUseCase
import com.pmb.core.platform.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SendAuthenticationInformationUseCase @Inject constructor(
    private val authenticationInformationRepository: AuthenticationInformationRepository
) : BaseUseCase<SendAuthenticationInformationParam, Boolean>() {
    override suspend fun execute(params: SendAuthenticationInformationParam): Flow<Result<Boolean>> =
        authenticationInformationRepository.sendAuthenticationInformation(params)
}