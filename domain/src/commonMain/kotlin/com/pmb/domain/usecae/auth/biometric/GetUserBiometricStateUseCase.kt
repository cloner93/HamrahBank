package com.pmb.domain.usecae.auth.biometric

import com.pmb.core.platform.BaseUseCase
import com.pmb.core.platform.Result
import com.pmb.domain.repository.auth.AuthRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetUserBiometricStateUseCase @Inject constructor(
    private val fingerPrintState: AuthRepository
) : BaseUseCase<Unit, Boolean>() {

    override suspend fun execute(params: Unit): Flow<Result<Boolean>> = flow {
        try {
            val isEnable = fingerPrintState.getFingerPrintState()
            emit(Result.Success(isEnable))

        } catch (e: Exception) {
            emit(Result.Error("not enabled.", e))
        }
    }
}