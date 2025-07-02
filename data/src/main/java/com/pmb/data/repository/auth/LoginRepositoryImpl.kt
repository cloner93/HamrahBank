package com.pmb.data.repository.auth

import com.pmb.core.platform.Result
import com.pmb.data.mapper.mapApiResult
import com.pmb.domain.model.LoginRequest
import com.pmb.domain.model.LoginResponse
import com.pmb.domain.model.RegisterVerifyResponse
import com.pmb.domain.repository.auth.LoginRepository
import com.pmb.network.NetworkManger
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val client: NetworkManger
) : LoginRepository {
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
}

fun RegisterVerifyResponse.mapToLoginResponse(): LoginResponse {
    return LoginResponse(
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