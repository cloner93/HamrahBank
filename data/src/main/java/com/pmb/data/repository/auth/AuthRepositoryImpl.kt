package com.pmb.data.repository.auth

import com.pmb.core.platform.Result
import com.pmb.data.mapper.authService.mapToLoginResponse
import com.pmb.data.mapper.authService.toDomain
import com.pmb.data.mapper.mapApiResult
import com.pmb.data.serviceProvider.local.LocalServiceProvider
import com.pmb.data.serviceProvider.remote.RemoteServiceProvider
import com.pmb.domain.model.LoginResponse
import com.pmb.domain.model.SendOtpRequest
import com.pmb.domain.model.SendOtpResponse
import com.pmb.domain.model.UserData
import com.pmb.domain.model.openAccount.AccountArchiveJobDocResponse
import com.pmb.domain.model.openAccount.accountVerifyCode.VerifyCodeResponse
import com.pmb.domain.model.openAccount.accountVerifyCode.accountType.FetchAccountTypeResponse
import com.pmb.domain.repository.auth.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val remoteServiceProvider: RemoteServiceProvider,
    private val localServiceProvider: LocalServiceProvider
) : AuthRepository {
    override suspend fun sendOtp(sendOtpRequest: SendOtpRequest): Flow<Result<SendOtpResponse>> {
        return remoteServiceProvider.getAuthService().sendOtp(sendOtpRequest).mapApiResult {
            if (it.first?.statusMessage == "موفق") {
                localServiceProvider.getUserDataStore().setUserData(
                    UserData(
                        customerId = sendOtpRequest.mobileNumber,
                        username = sendOtpRequest.userName,
                        password = sendOtpRequest.password
                    )
                )
            }
            it.second.toDomain()
        }
    }

    override fun login(
        customerId: String, username: String, password: String
    ): Flow<Result<LoginResponse>> {
        return remoteServiceProvider.getAuthService()
            .login(customerId = customerId, username = username, password = password).mapApiResult {
                it.second.mapToLoginResponse()
            }
    }

    override fun register(
        customerId: String, username: String, password: String
    ): Flow<Result<Boolean>> {
        return remoteServiceProvider.getAuthService()
            .register(customerId = customerId, username = username, password = password)
            .mapApiResult { /*metaData, data ->*/
                it.first.toDomain()
            }
    }

    override fun generateCode(
        nationalCode: String, mobileNo: String, birthDate: String
    ): Flow<Result<Boolean>> {
        return remoteServiceProvider.getAuthService().generateCode(
            nationalCode = nationalCode, mobileNo = mobileNo, birthDate = birthDate
        ).mapApiResult {
            it.first.toDomain()
        }
    }

    override fun verifyCode(
        verificationCode: Int, nationalCode: String, mobileNo: String, idSerial: String
    ): Flow<Result<VerifyCodeResponse>> {
        return remoteServiceProvider.getAuthService().accountVerifyCode(
            verificationCode = verificationCode,
            nationalCode = nationalCode,
            mobileNo = mobileNo,
            idSerial = idSerial
        ).mapApiResult {
            it.second
        }
    }

    override fun accountArchiveJobDoc(
        file: String, nationalCode: String
    ): Flow<Result<AccountArchiveJobDocResponse>> {
        return remoteServiceProvider.getAuthService().accountArchiveJobDoc(
            file = file, nationalCode = nationalCode
        ).mapApiResult { it.second }
    }

    override fun fetchAccountType(
        customerType: Int, nationalCode: String, mobileNo: String
    ): Flow<Result<FetchAccountTypeResponse>> {
        return remoteServiceProvider.getAuthService()
            .fetchAccountType(
                customerType = customerType,
                nationalCode = nationalCode,
                mobileNo = mobileNo
            ).mapApiResult { it.second }
    }
}