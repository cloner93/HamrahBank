package com.pmb.data.serviceProvider.remote.auth

import com.pmb.core.platform.Result
import com.pmb.domain.model.LoginResponse
import com.pmb.domain.model.RegisterVerifyResponse
import com.pmb.domain.model.SendOtpRequest
import com.pmb.domain.model.openAccount.AccountArchiveJobDocResponse
import com.pmb.domain.model.openAccount.FetchAdmittanceTextResponse
import com.pmb.domain.model.openAccount.FetchCommitmentResponse
import com.pmb.domain.model.openAccount.RegisterOpenAccountRequest
import com.pmb.domain.model.openAccount.RegisterOpenAccountResponse
import com.pmb.domain.model.openAccount.accountType.FetchAccountTypeResponse
import com.pmb.domain.model.openAccount.accountVerifyCode.VerifyCodeResponse
import com.pmb.domain.model.openAccount.branchName.FetchBranchListResponse
import com.pmb.domain.model.openAccount.cityName.FetchCityListResponse
import com.pmb.domain.model.openAccount.comissionFee.FetchCommissionFeeResponse
import com.pmb.domain.model.openAccount.jobLevel.FetchJobLevelResponse
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
    ): Flow<Result<SuccessData<Boolean>>>

    fun accountVerifyCode(
        verificationCode: Int, nationalCode: String, mobileNo: String, idSerial: String?
    ): Flow<Result<SuccessData<VerifyCodeResponse>>>

    fun accountArchiveJobDoc(
        file: String, nationalCode: String
    ): Flow<Result<SuccessData<AccountArchiveJobDocResponse>>>

    fun fetchJobLevel(): Flow<Result<SuccessData<FetchJobLevelResponse>>>

    fun fetchAccountType(
        nationalCode: String, mobileNo: String
    ): Flow<Result<SuccessData<FetchAccountTypeResponse>>>

    fun fetchCityList(
        stateCode: Int
    ): Flow<Result<SuccessData<FetchCityListResponse>>>

    fun fetchBranchList(
        stateCode: Int, cityCode: Int
    ): Flow<Result<SuccessData<FetchBranchListResponse>>>

    fun fetchCommitment(accType: Int): Flow<Result<SuccessData<FetchCommitmentResponse>>>

    fun fetchCommissionFee(): Flow<Result<SuccessData<FetchCommissionFeeResponse>>>

    fun fetchAdmittanceText(): Flow<Result<SuccessData<FetchAdmittanceTextResponse>>>

    fun registerOpenAccount(registerOpenAccountRequest: RegisterOpenAccountRequest): Flow<Result<SuccessData<RegisterOpenAccountResponse>>>

}