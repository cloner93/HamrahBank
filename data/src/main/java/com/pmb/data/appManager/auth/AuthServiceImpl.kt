package com.pmb.data.appManager.auth

import com.pmb.core.platform.Result
import com.pmb.data.mapper.authService.toData
import com.pmb.domain.model.LoginRequest
import com.pmb.domain.model.LoginResponse
import com.pmb.domain.model.RegisterRequest
import com.pmb.domain.model.RegisterVerifyResponse
import com.pmb.domain.model.SendOtpRequest
import com.pmb.model.SuccessData
import com.pmb.network.NetworkManger
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthServiceImpl @Inject constructor(
    private val client: NetworkManger
) : AuthService {
    override suspend fun sendOtp(sendOtpRequest: SendOtpRequest):
            Flow<Result<SuccessData<RegisterVerifyResponse>>> {
        return client.request<RegisterRequest, RegisterVerifyResponse>(
            "verify",
            sendOtpRequest.toData()
        )
    }

    override fun login(
        customerId: String,
        username: String,
        password: String
    ): Flow<Result<SuccessData<LoginResponse>>> {
        val req = LoginRequest(customerId = customerId, userName = username, password = password)
        return client.request<LoginRequest, LoginResponse>(endpoint = "login", data = req)
    }

    override fun register(
        customerId: String,
        username: String,
        password: String
    ): Flow<Result<SuccessData<Boolean>>> {
        val req = RegisterRequest(
            customerId = customerId,
            userName = username,
            password = password,
            vcode = 0
        )
        return client.request<RegisterRequest, Boolean>("register", req)
    }

}