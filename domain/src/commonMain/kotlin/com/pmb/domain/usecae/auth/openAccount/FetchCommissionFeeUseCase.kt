package com.pmb.domain.usecae.auth.openAccount

import com.pmb.core.platform.BaseUseCase
import com.pmb.core.platform.Result
import com.pmb.domain.model.openAccount.comissionFee.FetchCommissionFeeResponse
import com.pmb.domain.repository.auth.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchCommissionFeeUseCase @Inject constructor(
    val authRepository: AuthRepository
) : BaseUseCase<Unit, FetchCommissionFeeResponse>() {
    override suspend fun execute(params: Unit): Flow<Result<FetchCommissionFeeResponse>> {
        return authRepository.fetchCommissionFee()
    }
}