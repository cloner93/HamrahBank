package com.pmb.domain.usecae.auth

import com.pmb.core.platform.BaseUseCase
import com.pmb.core.platform.Result
import com.pmb.domain.model.LoginResponse
import com.pmb.domain.repository.auth.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RequestLoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) : BaseUseCase<RequestLoginParams, LoginResponse>() {

    override suspend fun execute(params: RequestLoginParams): Flow<Result<LoginResponse>> {
        // this is temp
        return authRepository.login(
            params.customerId,
            params.username,
            params.password,
            params.useFinger
        )
    }
}

data class RequestLoginParams(
    val customerId: String = "", // default value is temp
    val username: String,
    val password: String,
    val useFinger: Boolean = false
)
