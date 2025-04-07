package com.pmb.auth.domain.register.check_postal_code.useCase

import com.pmb.auth.domain.register.check_postal_code.entity.CheckPostalCodeRequest
import com.pmb.auth.domain.register.check_postal_code.entity.CheckPostalCodeResponse
import com.pmb.auth.domain.register.check_postal_code.repository.CheckPostalCodeRepository
import com.pmb.core.platform.BaseUseCase
import com.pmb.core.platform.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CheckPostalCodeUseCase @Inject constructor(
    private val checkPostalCodeRepository: CheckPostalCodeRepository
) : BaseUseCase<CheckPostalCodeRequest, CheckPostalCodeResponse>() {
    override suspend fun execute(params: CheckPostalCodeRequest): Flow<Result<CheckPostalCodeResponse>> =
        checkPostalCodeRepository.checkPostalCode(params)

}