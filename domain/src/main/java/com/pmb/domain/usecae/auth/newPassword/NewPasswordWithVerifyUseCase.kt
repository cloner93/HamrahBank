package com.pmb.domain.usecae.auth.newPassword

import com.pmb.core.platform.BaseUseCase
import com.pmb.core.platform.Result
import com.pmb.domain.model.changePassword.NewPasswordWithVerifyRequest
import com.pmb.domain.model.changePassword.NewPasswordWithVerifyResponse
import com.pmb.domain.repository.auth.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewPasswordWithVerifyUseCase @Inject constructor(
    private val authRepository: AuthRepository
) : BaseUseCase<NewPasswordWithVerifyParams, Boolean>() {
    override suspend fun execute(params: NewPasswordWithVerifyParams): Flow<Result<Boolean>> {
        return authRepository.newPasswordWithVerify(params.toRequest())
    }
}

data class NewPasswordWithVerifyParams(
    val nationalCode: String, val password: String
) {
    fun toRequest(): NewPasswordWithVerifyRequest {
        return NewPasswordWithVerifyRequest(
            nationalCode = nationalCode, password = password
        )
    }
}