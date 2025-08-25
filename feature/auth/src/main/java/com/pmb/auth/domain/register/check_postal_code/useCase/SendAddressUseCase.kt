package com.pmb.auth.domain.register.check_postal_code.useCase

import com.pmb.auth.domain.register.check_postal_code.entity.SendAddressRequest
import com.pmb.auth.domain.register.check_postal_code.entity.SendAddressResponse
import com.pmb.auth.domain.register.check_postal_code.repository.CheckPostalCodeRepository
import com.pmb.core.platform.BaseUseCase
import com.pmb.core.platform.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SendAddressUseCase @Inject constructor(private val checkPostalCodeRepository: CheckPostalCodeRepository) :
    BaseUseCase<SendAddressRequest, SendAddressResponse>() {
    override suspend fun execute(params: SendAddressRequest): Flow<Result<SendAddressResponse>> =
        checkPostalCodeRepository.checkAddress(params)

}