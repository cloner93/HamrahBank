package com.pmb.domain.usecae.auth

import com.pmb.core.platform.BaseUseCase
import com.pmb.core.platform.Result
import com.pmb.domain.model.UserData
import com.pmb.domain.repository.auth.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetUserDataUseCase @Inject constructor(
    private val authRepository: AuthRepository
) : BaseUseCase<Unit, UserData?>() {
    override suspend fun execute(params: Unit): Flow<Result<UserData?>> = flow {
        emit(Result.Loading)
        try {
            val userData = authRepository.getUserDataStore()
            emit(Result.Success(userData))
        } catch (e: Exception) {
            emit(Result.Error(message = e.message ?: "Unknown error", exception = e))
        }
    }

}