package com.pmb.domain.repository.auth

import com.pmb.core.platform.Result
import com.pmb.domain.model.LoginResponse
import com.pmb.domain.model.SendOtpRequest
import com.pmb.domain.model.SendOtpResponse
import com.pmb.domain.model.UserData
import com.pmb.domain.model.openAccount.AccountArchiveJobDocResponse
import com.pmb.domain.model.openAccount.CheckPostalCodeResponse
import com.pmb.domain.model.openAccount.FetchAdmittanceTextResponse
import com.pmb.domain.model.openAccount.FetchCardFormatResponse
import com.pmb.domain.model.openAccount.FetchCommitmentResponse
import com.pmb.domain.model.openAccount.RegisterOpenAccountRequest
import com.pmb.domain.model.openAccount.RegisterOpenAccountResponse
import com.pmb.domain.model.openAccount.accountType.FetchAccountTypeResponse
import com.pmb.domain.model.openAccount.accountVerifyCode.VerifyCodeResponse
import com.pmb.domain.model.openAccount.branchName.Branch
import com.pmb.domain.model.openAccount.cityName.City
import com.pmb.domain.model.openAccount.comissionFee.FetchCommissionFeeResponse
import com.pmb.domain.model.openAccount.jobLevel.JobLevel
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun getUserData(): Flow<Result<UserData?>>
    suspend fun logoutUser(): Flow<Result<Boolean>>
    suspend fun sendOtp(sendOtpRequest: SendOtpRequest): Flow<Result<SendOtpResponse>>
    fun login(customerId: String, username: String, password: String): Flow<Result<LoginResponse>>
    fun register(customerId: String, username: String, password: String): Flow<Result<Boolean>>
    fun generateCode(
        nationalCode: String, mobileNo: String, birthDate: String
    ): Flow<Result<Boolean>>

    fun verifyCode(
        verificationCode: Int, nationalCode: String, mobileNo: String, idSerial: String?
    ): Flow<Result<VerifyCodeResponse>>

    fun accountArchiveJobDoc(
        file: String, nationalCode: String
    ): Flow<Result<AccountArchiveJobDocResponse>>

    fun fetchJobLevel(): Flow<Result<List<JobLevel>>>

    fun fetchAccountType(
        nationalCode: String, mobileNo: String
    ): Flow<Result<FetchAccountTypeResponse>>

    fun fetchCityList(stateCode: Int): Flow<Result<List<City>>>

    fun fetchBranchList(
        stateCode: Int, cityCode: Int
    ): Flow<Result<List<Branch>>>

    fun fetchCommitment(accType: Int): Flow<Result<FetchCommitmentResponse>>

    fun fetchCommissionFee(): Flow<Result<FetchCommissionFeeResponse>>

    fun fetchAdmittanceText(): Flow<Result<FetchAdmittanceTextResponse>>

    fun registerOpenAccount(registerOpenAccountRequest: RegisterOpenAccountRequest): Flow<Result<RegisterOpenAccountResponse>>
    fun checkPostalCode(postCode: Int): Flow<Result<CheckPostalCodeResponse>>
    fun fetchCardFormat(): Flow<Result<List<FetchCardFormatResponse>>>
}