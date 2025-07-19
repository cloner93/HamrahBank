package com.pmb.domain.repository.auth

import com.pmb.core.platform.Result
import com.pmb.domain.model.LoginResponse
import com.pmb.domain.model.SendOtpRequest
import com.pmb.domain.model.SendOtpResponse
import com.pmb.domain.model.openAccount.AccountArchiveJobDocResponse
import com.pmb.domain.model.openAccount.accountType.FetchAccountTypeResponse
import com.pmb.domain.model.openAccount.accountVerifyCode.VerifyCodeResponse
import com.pmb.domain.model.openAccount.branchName.FetchBranchListResponse
import com.pmb.domain.model.openAccount.cityName.FetchCityListResponse
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun sendOtp(sendOtpRequest: SendOtpRequest): Flow<Result<SendOtpResponse>>
    fun login(customerId: String, username: String, password: String): Flow<Result<LoginResponse>>
    fun register(customerId: String, username: String, password: String): Flow<Result<Boolean>>
    fun generateCode(
        nationalCode: String, mobileNo: String, birthDate: String
    ): Flow<Result<Boolean>>

    fun verifyCode(
        verificationCode: Int, nationalCode: String, mobileNo: String, idSerial: String
    ): Flow<Result<VerifyCodeResponse>>

    fun accountArchiveJobDoc(
        file: String, nationalCode: String
    ): Flow<Result<AccountArchiveJobDocResponse>>

    fun fetchAccountType(
        customerType: Int, nationalCode: String, mobileNo: String
    ): Flow<Result<FetchAccountTypeResponse>>

    fun fetchCityList(stateCode: Int): Flow<Result<FetchCityListResponse>>

    fun fetchBranchList(
        mergeStatus: Int, stateCode: Int, cityCode: Int, organizationType: String
    ): Flow<Result<FetchBranchListResponse>>

}