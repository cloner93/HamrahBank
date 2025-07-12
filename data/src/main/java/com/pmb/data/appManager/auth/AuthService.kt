package com.pmb.data.appManager.auth

import com.pmb.core.platform.Result
import com.pmb.domain.model.LoginResponse
import com.pmb.domain.model.SendOtpRequest
import com.pmb.domain.model.SendOtpResponse
import kotlinx.coroutines.flow.Flow

interface AuthService {
    suspend fun sendOtp(sendOtpRequest: SendOtpRequest): Flow<Result<SendOtpResponse>>
    fun login(customerId: String, username: String, password: String): Flow<Result<LoginResponse>>
    fun register(customerId: String, username: String, password: String): Flow<Result<Boolean>>
}