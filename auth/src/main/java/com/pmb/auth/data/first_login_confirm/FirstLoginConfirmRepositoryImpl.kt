package com.pmb.auth.data.first_login_confirm

import com.pmb.auth.domain.first_login_confirm.entity.SendOtpRequest
import com.pmb.auth.domain.first_login_confirm.entity.SendOtpResponse
import com.pmb.auth.domain.first_login_confirm.repository.FirstLoginConfirmRepository
import com.pmb.core.platform.Result
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FirstLoginConfirmRepositoryImpl @Inject constructor() :
    FirstLoginConfirmRepository {

    override suspend fun sendOtp(sendOtpRequest: SendOtpRequest): Flow<Result<SendOtpResponse>> =
        flow {
            emit(Result.Loading)
            delay(2000)
            sendOtpRequest.takeIf { it.otp == "12345" && it.mobileNumber == "09308160417" }?.let {
                emit(Result.Success(SendOtpResponse(isSuccess = true)))
            } ?: run {
                emit(Result.Error(message = "otp is wrong"))
            }
        }
}