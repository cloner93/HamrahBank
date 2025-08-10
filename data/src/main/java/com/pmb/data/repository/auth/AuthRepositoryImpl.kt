package com.pmb.data.repository.auth

import com.pmb.core.platform.Result
import com.pmb.data.mapper.authService.toDomain
import com.pmb.data.mapper.mapApiResult
import com.pmb.data.serviceProvider.local.LocalServiceProvider
import com.pmb.data.serviceProvider.remote.RemoteServiceProvider
import com.pmb.domain.model.LoginResponse
import com.pmb.domain.model.SendOtpRequest
import com.pmb.domain.model.SendOtpResponse
import com.pmb.domain.model.UserData
import com.pmb.domain.model.changePassword.NewPasswordRequest
import com.pmb.domain.model.changePassword.NewPasswordWithEKYCRequest
import com.pmb.domain.model.changePassword.NewPasswordWithEKYCResponse
import com.pmb.domain.model.changePassword.NewPasswordWithVerifyRequest
import com.pmb.domain.model.changePassword.NewPasswordWithVerifyResponse
import com.pmb.domain.model.openAccount.AccountArchiveJobDocResponse
import com.pmb.domain.model.openAccount.CheckPostalCodeResponse
import com.pmb.domain.model.openAccount.FetchAdmittanceTextResponse
import com.pmb.domain.model.openAccount.FetchCommitmentResponse
import com.pmb.domain.model.openAccount.RegisterOpenAccountRequest
import com.pmb.domain.model.openAccount.RegisterOpenAccountResponse
import com.pmb.domain.model.openAccount.accountType.FetchAccountTypeResponse
import com.pmb.domain.model.openAccount.accountVerifyCode.VerifyCodeResponse
import com.pmb.domain.model.openAccount.branchName.Branch
import com.pmb.domain.model.openAccount.cityName.City
import com.pmb.domain.model.openAccount.comissionFee.FetchCommissionFeeResponse
import com.pmb.domain.model.openAccount.jobLevel.JobLevel
import com.pmb.domain.repository.auth.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val remoteServiceProvider: RemoteServiceProvider,
    private val localServiceProvider: LocalServiceProvider
) : AuthRepository {
    override suspend fun getUserData(): Flow<Result<UserData?>> = flow {
        emit(Result.Loading)
        try {
            val userData = localServiceProvider.getUserDataStore().getUserData()
            emit(Result.Success(userData))
        } catch (e: Exception) {
            emit(Result.Error(message = e.message ?: "Unknown error", exception = e))
        }
    }

    override suspend fun logoutUser(): Flow<Result<Boolean>> = flow {
        emit(Result.Loading)
        try {
            val result = localServiceProvider.getUserDataStore().logoutUser()
            emit(Result.Success(result))
        }catch (e:Exception){
            emit(Result.Error(message = e.message ?: "Unknown error", exception = e))
        }
    }

    override suspend fun sendOtp(sendOtpRequest: SendOtpRequest): Flow<Result<SendOtpResponse>> {
        return remoteServiceProvider.getAuthService().sendOtp(sendOtpRequest).mapApiResult {
            if (it.first?.statusMessage == "موفق") {
                it.second.let { user ->
                    localServiceProvider.getUserDataStore().setUserData(
                        UserData(
                            customerId = user.customerId.toString(),
                            username = sendOtpRequest.userName,
                            firstName = user.name ?:"",
                            lastName = user.family ?:"",
                            phoneNumber = sendOtpRequest.mobileNumber,
                            password = sendOtpRequest.password
                        )
                    )
                }
            }
            it.second.toDomain()
        }
    }

    override suspend fun login(
        customerId: String, username: String, password: String, useFinger: Boolean
    ): Flow<Result<LoginResponse>> {
        if (!useFinger)
            return remoteServiceProvider.getAuthService()
                .login(
                    customerId = customerId,
                    username = username,
                    password = password
                )
                .mapApiResult {
                    it.second
                }
        else {
            val userData = localServiceProvider.getUserDataStore().getUserData()

            return remoteServiceProvider.getAuthService()
                .login(
                    customerId = userData.customerId,
                    username = userData.username,
                    password = userData.password
                )
                .mapApiResult {
                    it.second
                }
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
        verificationCode: Int, nationalCode: String, mobileNo: String, idSerial: String?
    ): Flow<Result<VerifyCodeResponse>> {
        return remoteServiceProvider.getAuthService().accountVerifyCode(
            verificationCode = verificationCode,
            nationalCode = nationalCode,
            mobileNo = mobileNo,
            idSerial = null
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

    override fun fetchJobLevel(): Flow<Result<List<JobLevel>>> {
        return remoteServiceProvider.getAuthService().fetchJobLevel()
            .mapApiResult { it.second }
    }

    override fun fetchAccountType(
        nationalCode: String, mobileNo: String
    ): Flow<Result<FetchAccountTypeResponse>> {
        return remoteServiceProvider.getAuthService().fetchAccountType(
            nationalCode = nationalCode, mobileNo = mobileNo
        ).mapApiResult { it.second }
    }

    override fun fetchCityList(stateCode: Int): Flow<Result<List<City>>> {
        return remoteServiceProvider.getAuthService().fetchCityList(stateCode)
            .mapApiResult { it.second }
    }

    override fun fetchBranchList(
        stateCode: Int, cityCode: Int
    ): Flow<Result<List<Branch>>> {
        return remoteServiceProvider.getAuthService().fetchBranchList(
            stateCode = stateCode,
            cityCode = cityCode,
        ).mapApiResult { it.second }
    }

    override fun fetchCommitment(accType: Int): Flow<Result<FetchCommitmentResponse>> {
        return remoteServiceProvider.getAuthService().fetchCommitment(accType = accType)
            .mapApiResult { it.second }
    }

    override fun fetchCommissionFee(): Flow<Result<FetchCommissionFeeResponse>> {
        return remoteServiceProvider.getAuthService().fetchCommissionFee()
            .mapApiResult { it.second }
    }

    override fun fetchAdmittanceText(): Flow<Result<FetchAdmittanceTextResponse>> {
        return remoteServiceProvider.getAuthService().fetchAdmittanceText()
            .mapApiResult { it.second }
    }

    override fun registerOpenAccount(registerOpenAccountRequest: RegisterOpenAccountRequest): Flow<Result<RegisterOpenAccountResponse>> {
        return remoteServiceProvider.getAuthService()
            .registerOpenAccount(registerOpenAccountRequest)
            .mapApiResult { it.second }
    }

    override suspend fun getFingerPrintState(): Boolean {
        return localServiceProvider.getBiometric().getBiometricState()
    }

    override suspend fun setFingerPrintState(state: Boolean) {
        localServiceProvider.getBiometric().setBiometricState(state)
    }

    override fun checkPostalCode(postCode: Int): Flow<Result<CheckPostalCodeResponse>> {
        return remoteServiceProvider.getAuthService().checkPostCode(postCode).mapApiResult {
            it.second
        }
    }

    override fun newPassword(newPasswordRequest: NewPasswordRequest): Flow<Result<Boolean>> = flow {
        emit(Result.Loading)
        remoteServiceProvider.getAuthService().newPassword(newPasswordRequest)
            .mapApiResult { result ->
                if (result.first?.statusMessage == "موفق") {
                    Result.Success(true)
                } else {
                    Result.Error("Failed to set new password")
                }
            }
    }

    override fun newPasswordFetchAdmittanceText(): Flow<Result<FetchAdmittanceTextResponse>> {
        return remoteServiceProvider.getAuthService().newPasswordFetchAdmittanceText()
            .mapApiResult { it.second }
    }

    override fun newPasswordWithEKYC(newPasswordWithEKYCRequest: NewPasswordWithEKYCRequest): Flow<Result<NewPasswordWithEKYCResponse>> {
        return remoteServiceProvider.getAuthService().newPasswordWithEKYC(newPasswordWithEKYCRequest)
            .mapApiResult { it.second }
    }

    override fun newPasswordWithVerify(newPasswordWithVerifyRequest: NewPasswordWithVerifyRequest): Flow<Result<NewPasswordWithVerifyResponse>> {
        return remoteServiceProvider.getAuthService().newPasswordWithVerify(newPasswordWithVerifyRequest)
            .mapApiResult { it.second }
    }
}