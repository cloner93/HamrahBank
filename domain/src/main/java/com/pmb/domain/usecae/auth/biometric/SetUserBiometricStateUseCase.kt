package com.pmb.domain.usecae.auth.biometric

import com.pmb.core.platform.BaseUseCase
import com.pmb.core.platform.Result
import com.pmb.domain.repository.auth.AuthRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SetUserBiometricStateUseCase @Inject constructor(
    private val fingerPrintState: AuthRepository
) : BaseUseCase<Boolean, Boolean>() {

    override suspend fun execute(params: Boolean): Flow<Result<Boolean>> = flow {
        try {
            fingerPrintState.setFingerPrintState(params)

        } catch (e: Exception) {
            emit(Result.Error("not enabled.", e))
        }
    }
}