package com.pmb.auth.domain.register.check_postal_code.repository

import com.pmb.auth.domain.register.check_postal_code.entity.CheckPostalCodeRequest
import com.pmb.auth.domain.register.check_postal_code.entity.CheckPostalCodeResponse
import com.pmb.auth.domain.register.check_postal_code.entity.SendAddressRequest
import com.pmb.auth.domain.register.check_postal_code.entity.SendAddressResponse
import com.pmb.core.platform.Result
import kotlinx.coroutines.flow.Flow

interface CheckPostalCodeRepository {
    fun checkPostalCode(params: CheckPostalCodeRequest): Flow<Result<CheckPostalCodeResponse>>
    fun checkAddress(params: SendAddressRequest): Flow<Result<SendAddressResponse>>
}