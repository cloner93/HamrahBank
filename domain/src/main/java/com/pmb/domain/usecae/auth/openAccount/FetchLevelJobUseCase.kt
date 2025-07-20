package com.pmb.domain.usecae.auth.openAccount

import com.pmb.core.platform.BaseUseCase
import com.pmb.core.platform.Result
import com.pmb.domain.model.openAccount.jobLevel.FetchJobLevelResponse
import com.pmb.domain.repository.auth.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchLevelJobUseCase @Inject constructor(
    private val authRepository: AuthRepository
) : BaseUseCase<Unit, FetchJobLevelResponse>() {
    override suspend fun execute(params: Unit): Flow<Result<FetchJobLevelResponse>> {
        return authRepository.fetchJobLevel()
    }
}