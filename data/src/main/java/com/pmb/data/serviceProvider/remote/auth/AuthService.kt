package com.pmb.data.serviceProvider.remote.auth

import com.pmb.core.platform.Result
import com.pmb.domain.model.LoginResponse
import com.pmb.domain.model.RegisterVerifyResponse
import com.pmb.domain.model.SendOtpRequest
import com.pmb.domain.model.openAccount.GenerateCodeResponse
import com.pmb.domain.model.openAccount.accountVerifyCode.VerifyCodeResponse
import com.pmb.model.SuccessData
import kotlinx.coroutines.flow.Flow

interface AuthService {
    suspend fun sendOtp(sendOtpRequest: SendOtpRequest): Flow<Result<SuccessData<RegisterVerifyResponse>>>
    fun login(
        customerId: String, username: String, password: String
    ): Flow<Result<SuccessData<LoginResponse>>>

    fun register(
        customerId: String, username: String, password: String
    ): Flow<Result<SuccessData<Boolean>>>

    fun generateCode(
        nationalCode: String, mobileNo: String, birthDate: String
    ): Flow<Result<SuccessData<GenerateCodeResponse>>>

    fun accountVerifyCode(
        verificationCode: Int, nationalCode: String, mobileNo: String, idSerial: String
    ) : Flow<Result<SuccessData<VerifyCodeResponse>>>
}