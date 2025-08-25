package com.pmb.domain.usecae.auth.openAccount

import com.pmb.core.platform.BaseUseCase
import com.pmb.core.platform.Result
import com.pmb.domain.model.openAccount.FetchCommitmentResponse
import com.pmb.domain.repository.auth.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchCommitmentUseCase @Inject constructor(
    private val authRepository: AuthRepository
) : BaseUseCase<FetchCommitmentParams, FetchCommitmentResponse>() {
    override suspend fun execute(params: FetchCommitmentParams): Flow<Result<FetchCommitmentResponse>> {
        return authRepository.fetchCommitment(accType = params.accType)
    }
}

data class FetchCommitmentParams(val accType: Int)