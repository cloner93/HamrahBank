package com.pmb.auth.data.register.check_postal_code

import com.pmb.auth.domain.register.check_postal_code.entity.CheckPostalCodeRequest
import com.pmb.auth.domain.register.check_postal_code.entity.CheckPostalCodeResponse
import com.pmb.auth.domain.register.check_postal_code.entity.SendAddressRequest
import com.pmb.auth.domain.register.check_postal_code.entity.SendAddressResponse
import com.pmb.auth.domain.register.check_postal_code.repository.CheckPostalCodeRepository
import com.pmb.core.platform.Result
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CheckPostalCodeRepositoryImpl @Inject constructor() : CheckPostalCodeRepository {
    override fun checkPostalCode(params: CheckPostalCodeRequest): Flow<Result<CheckPostalCodeResponse>> =
        flow {
            emit(Result.Loading)
            params.postalCode.takeIf { it.isNotEmpty() && it == "1234567890" }?.let {
                emit(
                    Result.Success(
                        CheckPostalCodeResponse(
                            isSuccess = true,
                            address = "تهران، کوی نصر، خیابان ۲۷، پلاک ۱۵، واحد ۳"
                        )
                    )
                )
            } ?: run {
                emit(Result.Error(message = "Your postal code is not valid"))
            }
        }

    override fun checkAddress(params: SendAddressRequest): Flow<Result<SendAddressResponse>> =
        flow {
            emit(Result.Loading)
            params.postalCode.takeIf { it.isNotEmpty() }?.let {
                emit(
                    Result.Success(
                        SendAddressResponse(
                            isSuccess = true,
                        )
                    )
                )
            } ?: run {
                emit(Result.Error(message = "Your service failed"))
            }
        }

}