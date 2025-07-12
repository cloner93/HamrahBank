package com.pmb.data.appManager.auth

import com.pmb.core.platform.Result
import com.pmb.data.mapper.authService.mapToLoginResponse
import com.pmb.data.mapper.mapApiResult
import com.pmb.data.mapper.authService.toData
import com.pmb.data.mapper.authService.toDomain
import com.pmb.domain.model.LoginRequest
import com.pmb.domain.model.LoginResponse
import com.pmb.domain.model.RegisterRequest
import com.pmb.domain.model.RegisterVerifyResponse
import com.pmb.domain.model.SendOtpRequest
import com.pmb.domain.model.SendOtpResponse
import com.pmb.network.NetworkManger
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthServiceImpl @Inject constructor(
    private val client: NetworkManger
) : AuthService {
    override suspend fun sendOtp(sendOtpRequest: SendOtpRequest): Flow<Result<SendOtpResponse>> {
        return client.request<RegisterRequest, RegisterVerifyResponse>(
            "verify",
            sendOtpRequest.toData()
        ).mapApiResult {
            it.second.toDomain()
        }
    }

    override fun login(
        customerId: String,
        username: String,
        password: String
    ): Flow<Result<LoginResponse>> {
        val req = LoginRequest(customerId = customerId, userName = username, password = password)
        return client.request<LoginRequest, RegisterVerifyResponse>(endpoint = "login", data = req)
            .mapApiResult {
                it.second.mapToLoginResponse()
            }
    }

    override fun register(
        customerId: String,
        username: String,
        password: String
    ): Flow<Result<Boolean>> {
        val req = RegisterRequest(
            customerId = customerId,
            userName = username,
            password = password,
            vcode = 0
        )
        return client.request<RegisterRequest, Unit>("register", req)
            .mapApiResult { /*metaData, data ->*/
                it.first.toDomain()
            }
    }

}