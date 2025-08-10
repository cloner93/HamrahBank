package com.pmb.data.serviceProvider.remote.auth

import com.pmb.core.platform.Result
import com.pmb.data.mapper.authService.toData
import com.pmb.data.model.AnyModel
import com.pmb.domain.model.LoginRequest
import com.pmb.domain.model.LoginResponse
import com.pmb.domain.model.RegisterRequest
import com.pmb.domain.model.RegisterVerifyResponse
import com.pmb.domain.model.SendOtpRequest
import com.pmb.domain.model.changePassword.NewPasswordRequest
import com.pmb.domain.model.changePassword.NewPasswordWithEKYCRequest
import com.pmb.domain.model.changePassword.NewPasswordWithEKYCResponse
import com.pmb.domain.model.changePassword.NewPasswordWithVerifyRequest
import com.pmb.domain.model.changePassword.NewPasswordWithVerifyResponse
import com.pmb.domain.model.openAccount.AccountArchiveJobDocRequest
import com.pmb.domain.model.openAccount.AccountArchiveJobDocResponse
import com.pmb.domain.model.openAccount.CheckPostCodeRequest
import com.pmb.domain.model.openAccount.CheckPostalCodeResponse
import com.pmb.domain.model.openAccount.FetchCommitmentRequest
import com.pmb.domain.model.openAccount.FetchCommitmentResponse
import com.pmb.domain.model.openAccount.GenerateCodeRequest
import com.pmb.domain.model.openAccount.accountType.FetchAccountTypeRequest
import com.pmb.domain.model.openAccount.accountType.FetchAccountTypeResponse
import com.pmb.domain.model.openAccount.accountVerifyCode.VerifyCodeRequest
import com.pmb.domain.model.openAccount.accountVerifyCode.VerifyCodeResponse
import com.pmb.domain.model.openAccount.branchName.FetchBranchListRequest
import com.pmb.domain.model.openAccount.cityName.FetchCityListRequest
import com.pmb.domain.model.openAccount.FetchAdmittanceTextResponse
import com.pmb.domain.model.openAccount.RegisterOpenAccountRequest
import com.pmb.domain.model.openAccount.RegisterOpenAccountResponse
import com.pmb.domain.model.openAccount.branchName.Branch
import com.pmb.domain.model.openAccount.cityName.City
import com.pmb.domain.model.openAccount.comissionFee.FetchCommissionFeeResponse
import com.pmb.domain.model.openAccount.jobLevel.JobLevel
import com.pmb.model.SuccessData
import com.pmb.network.NetworkManger
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthServiceImpl @Inject constructor(
    private val client: NetworkManger
) : AuthService {
    override suspend fun sendOtp(sendOtpRequest: SendOtpRequest): Flow<Result<SuccessData<RegisterVerifyResponse>>> {
        return client.request<RegisterRequest, RegisterVerifyResponse>(
            "verify", sendOtpRequest.toData()
        )
    }

    override fun login(
        customerId: String, username: String, password: String
    ): Flow<Result<SuccessData<LoginResponse>>> {
        val req = LoginRequest(customerId = customerId, userName = username, password = password)
        return client.request<LoginRequest, LoginResponse>(endpoint = "login", data = req)
    }

    override fun register(
        customerId: String, username: String, password: String
    ): Flow<Result<SuccessData<Boolean>>> {
        val req = RegisterRequest(
            customerId = customerId, userName = username, password = password, vcode = 0
        )
        return client.request<RegisterRequest, Boolean>("register", req)
    }

    override fun generateCode(
        nationalCode: String, mobileNo: String, birthDate: String
    ): Flow<Result<SuccessData<Boolean>>> {
        val req = GenerateCodeRequest(
            nationalCode = nationalCode, mobileNo = mobileNo, birthDate = birthDate
        )
        return client.request<GenerateCodeRequest, Boolean>(
            "openAccount/generateCode", req
        )
    }

    override fun accountVerifyCode(
        verificationCode: Int, nationalCode: String, mobileNo: String, idSerial: String?
    ): Flow<Result<SuccessData<VerifyCodeResponse>>> {
        val req = VerifyCodeRequest(
            verificationCode = verificationCode,
            nationalCode = nationalCode,
            mobileNo = mobileNo,
            idSerial = idSerial
        )
        return client.request<VerifyCodeRequest, VerifyCodeResponse>(
            "openAccount/accountVerifyCode", req
        )
    }

    override fun accountArchiveJobDoc(
        file: String, nationalCode: String
    ): Flow<Result<SuccessData<AccountArchiveJobDocResponse>>> {
        val req = AccountArchiveJobDocRequest(file = file, nationalCode = nationalCode)
        return client.request<AccountArchiveJobDocRequest, AccountArchiveJobDocResponse>(
            "openAccount/accountArchiveJobDoc", req
        )
    }

    override fun fetchJobLevel(): Flow<Result<SuccessData<List<JobLevel>>>> {
        return client.request<Unit,List<JobLevel>>(
            "openAccount/accountFetchLevelJob"
        )
    }

    override fun fetchAccountType(
       nationalCode: String, mobileNo: String
    ): Flow<Result<SuccessData<FetchAccountTypeResponse>>> {
        val req = FetchAccountTypeRequest(
            nationalCode = nationalCode, mobileNo = mobileNo
        )
        return client.request<FetchAccountTypeRequest, FetchAccountTypeResponse>(
            "openAccount/fetchAccountType", req
        )
    }

    override fun fetchCityList(stateCode: Int): Flow<Result<SuccessData<List<City>>>> {
        val req = FetchCityListRequest(stateCode = stateCode)
        return client.request<FetchCityListRequest, List<City>>(
            "openAccount/fetchCityList", req
        )
    }

    override fun fetchBranchList(
        stateCode: Int, cityCode: Int
    ): Flow<Result<SuccessData<List<Branch>>>> {
        val req = FetchBranchListRequest(
            stateCode = stateCode,
            cityCode = cityCode,
        )
        return client.request<FetchBranchListRequest, List<Branch>>(
            "openAccount/fetchBranchList", req
        )
    }

    override fun fetchCommitment(accType: Int): Flow<Result<SuccessData<FetchCommitmentResponse>>> {
        val req = FetchCommitmentRequest(accType = accType)
        return client.request<FetchCommitmentRequest,FetchCommitmentResponse>(
            "openAccount/fetchCommitment",req
        )
    }

    override fun fetchCommissionFee(): Flow<Result<SuccessData<FetchCommissionFeeResponse>>> {
        return client.request<Unit, FetchCommissionFeeResponse>(
            "openAccount/fetchCommission"
        )
    }

    override fun fetchAdmittanceText(): Flow<Result<SuccessData<FetchAdmittanceTextResponse>>> {
        return client.request<Unit, FetchAdmittanceTextResponse>(
           "openAccount/fetchAdmittanceText"
        )
    }

    override fun registerOpenAccount(registerOpenAccountRequest: RegisterOpenAccountRequest): Flow<Result<SuccessData<RegisterOpenAccountResponse>>> {
        return client.request<RegisterOpenAccountRequest,RegisterOpenAccountResponse>(
            "openAccount/registerRequest", registerOpenAccountRequest
        )
    }

    override fun checkPostCode(postCode: Int): Flow<Result<SuccessData<CheckPostalCodeResponse>>> {
        val req = CheckPostCodeRequest(postcode = postCode)
        return client.request<CheckPostCodeRequest,CheckPostalCodeResponse>(
            "openAccount/postCodeInquiry", req
        )
    }

    override fun newPassword(newPasswordRequest: NewPasswordRequest): Flow<Result<SuccessData<AnyModel>>> {
        return client.request<NewPasswordRequest,AnyModel>(
            "changePassword/newPassword", newPasswordRequest
        )
    }

    override fun newPasswordFetchAdmittanceText(): Flow<Result<SuccessData<FetchAdmittanceTextResponse>>> {
        return client.request<Unit, FetchAdmittanceTextResponse>(
            "changePassword/fetchAdmittanceText"
        )
    }

    override fun newPasswordWithEKYC(newPasswordWithEKYCRequest: NewPasswordWithEKYCRequest): Flow<Result<SuccessData<NewPasswordWithEKYCResponse>>> {
        return client.request<NewPasswordWithEKYCRequest, NewPasswordWithEKYCResponse>(
            "changePassword/withEkyc"
        )
    }

    override fun newPasswordWithVerify(newPasswordWithVerifyRequest: NewPasswordWithVerifyRequest): Flow<Result<SuccessData<NewPasswordWithVerifyResponse>>> {
        return client.request<NewPasswordWithVerifyRequest, NewPasswordWithVerifyResponse>(
            "changePassword/withVerify"
        )
    }

}