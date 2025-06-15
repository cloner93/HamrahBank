package com.pmb.auth.domain.login.usecase

import com.pmb.core.platform.BaseUseCase
import com.pmb.core.platform.Result
import com.pmb.domain.model.dto.LoginResponse
import com.pmb.domain.repository.auth.login.LoginRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RequestLoginUseCase @Inject constructor(
    private val loginRepository: LoginRepository
) : BaseUseCase<RequestLoginParams, LoginResponse>() {

    override suspend fun execute(params: RequestLoginParams): Flow<Result<LoginResponse>> {
        // this is temp
        return loginRepository.login(params.username, params.password)
    }
}

data class RequestLoginParams(val username: String, val password: String)
