package com.pmb.domain.usecae.auth.openAccount

import com.pmb.core.platform.BaseUseCase
import com.pmb.core.platform.Result
import com.pmb.domain.model.openAccount.CheckPostalCodeResponse
import com.pmb.domain.repository.auth.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CheckPostCodeUseCase @Inject constructor(
    private val authRepository: AuthRepository
): BaseUseCase<Int, CheckPostalCodeResponse>() {
    override suspend fun execute(params: Int): Flow<Result<CheckPostalCodeResponse>> {
        return authRepository.checkPostalCode(params)
    }
}