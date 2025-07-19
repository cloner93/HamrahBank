package com.pmb.domain.usecae.auth

import com.pmb.core.platform.BaseUseCase
import com.pmb.core.platform.Result
import com.pmb.domain.model.UserData
import com.pmb.domain.repository.auth.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserDataUseCase @Inject constructor(
    private val authRepository: AuthRepository
) : BaseUseCase<Unit, UserData?>() {
    override suspend fun execute(params: Unit): Flow<Result<UserData?>> {
        return authRepository.getUserData()
    }

}