package com.pmb.domain.usecae.auth.openAccount

import com.pmb.core.platform.BaseUseCase
import com.pmb.core.platform.Result
import com.pmb.domain.model.openAccount.FetchCardFormatResponse
import com.pmb.domain.repository.auth.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchCardFormatUseCase @Inject constructor(
    private val authRepository: AuthRepository
) : BaseUseCase<Unit, List<FetchCardFormatResponse>>() {
    override suspend fun execute(params: Unit): Flow<Result<List<FetchCardFormatResponse>>> {
        return authRepository.fetchCardFormat()
    }

}