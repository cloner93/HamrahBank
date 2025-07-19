package com.pmb.domain.usecae.auth.openAccount

import com.pmb.core.platform.BaseUseCase
import com.pmb.core.platform.Result
import com.pmb.domain.model.openAccount.accountVerifyCode.VerifyCodeResponse
import com.pmb.domain.repository.auth.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AccountVerifyCodeUseCase @Inject constructor(
    private val authRepository: AuthRepository
) : BaseUseCase<VerifyCodeParams, VerifyCodeResponse>() {
    override suspend fun execute(params: VerifyCodeParams): Flow<Result<VerifyCodeResponse>> {
        return authRepository.verifyCode(
            verificationCode = params.verificationCode,
            nationalCode = params.nationalCode,
            mobileNo = params.mobileNo,
            idSerial = params.idSerial
        )
    }
}

data class VerifyCodeParams(
    val verificationCode: Int, val nationalCode: String, val mobileNo: String, val idSerial: String
)