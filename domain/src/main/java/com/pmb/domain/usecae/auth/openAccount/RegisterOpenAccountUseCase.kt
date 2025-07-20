package com.pmb.domain.usecae.auth.openAccount

import com.pmb.core.platform.BaseUseCase
import com.pmb.core.platform.Result
import com.pmb.domain.model.openAccount.RegisterOpenAccountRequest
import com.pmb.domain.model.openAccount.RegisterOpenAccountResponse
import com.pmb.domain.repository.auth.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RegisterOpenAccountUseCase @Inject constructor(
    private val authRepository: AuthRepository
) : BaseUseCase<RegisterOpenAccountParams, RegisterOpenAccountResponse>() {
    override suspend fun execute(params: RegisterOpenAccountParams): Flow<Result<RegisterOpenAccountResponse>> {
        return authRepository.registerOpenAccount(
            params.mapToRequest()
        )
    }
}

private fun RegisterOpenAccountParams.mapToRequest(): RegisterOpenAccountRequest {
    return RegisterOpenAccountRequest(
        accType = accType,
        address = address,
        birthCityCode = birthCityCode,
        birthDate = birthDate,
        branch = branch,
        cardFormatId = cardFormatId,
        cardIssueType = cardIssueType,
        cardReq = cardReq,
        cinCpId = cinCpId,
        ctrApId = ctrApId,
        education = education,
        envCode = envCode,
        intBankReq = intBankReq,
        issueCityCode = issueCityCode,
        issueDate = issueDate,
        issueRgnCode = issueRgnCode,
        jobCode = jobCode,
        mobBankReq = mobBankReq,
        mobileNo = mobileNo,
        nationalCode = nationalCode,
        postcode = postcode,
        refId = refId,
        seriMeli = seriMeli,
        serialMeli = serialMeli,
        signData = signData,
        tel = tel,
        jobFirstLevelCode = jobFirstLevelCode,
        jobSecondLevelCode = jobSecondLevelCode,
        jobThirdLevelCode = jobThirdLevelCode
    )
}

data class RegisterOpenAccountParams(
    val accType: Int,
    val address: String,
    val birthCityCode: Int,
    val birthDate: String,
    val branch: Int,
    val cardFormatId: Int,
    val cardIssueType: Int,
    val cardReq: Int,
    val cinCpId: Int,
    val ctrApId: Int,
    val education: Int,
    val envCode: Int,
    val intBankReq: Int,
    val issueCityCode: Int,
    val issueDate: String,
    val issueRgnCode: Int,
    val jobCode: Int,
    val mobBankReq: Int,
    val mobileNo: String,
    val nationalCode: String,
    val postcode: Long,
    val refId: Long,
    val seriMeli: String,
    val serialMeli: String,
    val signData: String,
    val tel: String,
    val jobFirstLevelCode: Int,
    val jobSecondLevelCode: Int,
    val jobThirdLevelCode: Int
)