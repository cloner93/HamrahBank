package com.pmb.auth.domain.login.usecase

import com.pmb.auth.domain.login.entity.UserEntity
import com.pmb.auth.domain.login.repository.LoginRepository
import com.pmb.core.platform.BaseUseCase
import com.pmb.core.platform.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class RequestLoginUseCase @Inject constructor(
    private val loginRepository: LoginRepository
) : BaseUseCase<RequestLoginParams, UserEntity>() {

    override suspend fun execute(params: RequestLoginParams): Flow<Result<UserEntity>> {
        // Call the login function from the repository and return the first result
        return loginRepository.login(params.username, params.password)
    }
}

data class RequestLoginParams(val username: String, val password: String)
