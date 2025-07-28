package com.pmb.domain.usecae.auth

import com.pmb.core.platform.BaseUseCase
import com.pmb.core.platform.Result
import com.pmb.domain.repository.auth.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LogoutUserUseCase @Inject constructor(
    private val authRepository: AuthRepository
) : BaseUseCase<Unit, Boolean>() {
    override suspend fun execute(params: Unit): Flow<Result<Boolean>> {
        return authRepository.logoutUser()
    }
}