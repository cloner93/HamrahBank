package com.pmb.data.repository.auth

import com.pmb.core.platform.Result
import com.pmb.data.mapper.mapApiResult
import com.pmb.domain.model.RegisterRequest
import com.pmb.domain.model.RegisterVerifyResponse
import com.pmb.domain.model.SendOtpRequest
import com.pmb.domain.model.SendOtpResponse
import com.pmb.domain.repository.auth.FirstLoginConfirmRepository
import com.pmb.network.NetworkManger
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FirstLoginConfirmRepositoryImpl @Inject constructor(
    private val client: NetworkManger
) : FirstLoginConfirmRepository {
    override suspend fun sendOtp(sendOtpRequest: SendOtpRequest): Flow<Result<SendOtpResponse>> {
        return client.request<RegisterRequest, RegisterVerifyResponse>(
            "verify",
            sendOtpRequest.mapTooData()
        ).mapApiResult {
            it.second.toDomain()
        }
    }
}

fun SendOtpRequest.mapTooData(): RegisterRequest {
    return RegisterRequest(
        customerId = this.mobileNumber,
        userName = this.userName,
        password = this.password,
        vcode = this.otp.toInt()
    )
}

fun RegisterVerifyResponse.toDomain(): SendOtpResponse {

    return SendOtpResponse(
        isSuccess = true,
        customerId = this.customerId,
        desKey = this.desKey,
        passwordX = this.passwordX,
        securePassword = this.securePassword,
        userName = this.userName,
        email = this.email,
        imeiNo = this.imeiNO
    )
}