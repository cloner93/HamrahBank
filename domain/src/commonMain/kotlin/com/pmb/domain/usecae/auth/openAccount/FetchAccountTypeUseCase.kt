package com.pmb.domain.usecae.auth.openAccount

import com.pmb.core.platform.BaseUseCase
import com.pmb.core.platform.Result
import com.pmb.domain.model.openAccount.accountType.FetchAccountTypeResponse
import com.pmb.domain.repository.auth.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchAccountTypeUseCase @Inject constructor(
    private val authRepository: AuthRepository
) : BaseUseCase<FetchAccountTypeParams, FetchAccountTypeResponse>() {
    override suspend fun execute(params: FetchAccountTypeParams): Flow<Result<FetchAccountTypeResponse>> {
        return authRepository.fetchAccountType(
            nationalCode = params.nationalCode,
            mobileNo = params.mobileNo
        )
    }
}

data class FetchAccountTypeParams(
     val nationalCode: String, val mobileNo: String
)