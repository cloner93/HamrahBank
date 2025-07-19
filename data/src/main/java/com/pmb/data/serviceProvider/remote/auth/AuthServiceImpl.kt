package com.pmb.data.serviceProvider.remote.auth

import com.pmb.core.platform.Result
import com.pmb.data.mapper.authService.toData
import com.pmb.domain.model.LoginRequest
import com.pmb.domain.model.LoginResponse
import com.pmb.domain.model.RegisterRequest
import com.pmb.domain.model.RegisterVerifyResponse
import com.pmb.domain.model.SendOtpRequest
import com.pmb.domain.model.openAccount.GenerateCodeRequest
import com.pmb.domain.model.openAccount.GenerateCodeResponse
import com.pmb.domain.model.openAccount.accountVerifyCode.VerifyCodeRequest
import com.pmb.domain.model.openAccount.accountVerifyCode.VerifyCodeResponse
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

    override fun generateCode(
        nationalCode: String,
        mobileNo: String,
        birthDate: String
    ): Flow<Result<SuccessData<GenerateCodeResponse>>> {
        val req = GenerateCodeRequest(nationalCode = nationalCode, mobileNo = mobileNo,
            birthDate = birthDate
        )
        return client.request<GenerateCodeRequest,GenerateCodeResponse>("openAccount/generateCode",req)
    }

    override fun accountVerifyCode(
        verificationCode: Int,
        nationalCode: String,
        mobileNo: String,
        idSerial: String
    ): Flow<Result<SuccessData<VerifyCodeResponse>>> {
        val req = VerifyCodeRequest(
            verificationCode = verificationCode,
            nationalCode = nationalCode,
            mobileNo = mobileNo,
            idSerial = idSerial
        )
        return client.request<VerifyCodeRequest,VerifyCodeResponse>("openAccount/accountVerifyCode",req)
    }

}