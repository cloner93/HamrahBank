package com.pmb.domain.usecae.auth.newPassword

import com.pmb.core.platform.BaseUseCase
import com.pmb.core.platform.Result
import com.pmb.domain.model.changePassword.NewPasswordWithEKYCRequest
import com.pmb.domain.model.changePassword.NewPasswordWithEKYCResponse
import com.pmb.domain.repository.auth.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewPasswordWithEKYCUseCase @Inject constructor(
    private val authRepository: AuthRepository
) : BaseUseCase<NewPasswordWithEKYCParams, NewPasswordWithEKYCResponse>() {
    override suspend fun execute(params: NewPasswordWithEKYCParams): Flow<Result<NewPasswordWithEKYCResponse>> {
        return authRepository.newPasswordWithEKYC(params.toRequest())
    }
}

data class NewPasswordWithEKYCParams(
    val admittanceText: String,
    val cardSerialNo: String,
    val mobileNumber: String,
    val nationalCode: String,
    val authImage: String,
    val authVideo: String
) {
    fun toRequest(): NewPasswordWithEKYCRequest {
        return NewPasswordWithEKYCRequest(
            admittanceText = admittanceText,
            cardSerialNo = cardSerialNo,
            mobileNumber = mobileNumber,
            nationalCode = nationalCode,
            authImage = authImage,
            authVideo = authVideo
        )
    }
}