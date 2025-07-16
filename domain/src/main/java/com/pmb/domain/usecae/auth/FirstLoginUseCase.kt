package com.pmb.domain.usecae.auth

import com.pmb.core.platform.BaseUseCase
import com.pmb.core.platform.Result
import com.pmb.domain.repository.auth.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FirstLoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) : BaseUseCase<FirstLoginStepRequest, Boolean>() {
    override suspend fun execute(params: FirstLoginStepRequest): Flow<Result<Boolean>> {
        return authRepository.register(
            customerId = params.mobileNumber,
            username = params.userName,
            password = params.password
        )
    }
}

data class FirstLoginStepRequest(
    val mobileNumber: String,
    val userName: String,
    val password: String
)