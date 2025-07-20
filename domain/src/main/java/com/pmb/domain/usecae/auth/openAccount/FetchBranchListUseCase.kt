package com.pmb.domain.usecae.auth.openAccount

import com.pmb.core.platform.BaseUseCase
import com.pmb.core.platform.Result
import com.pmb.domain.model.openAccount.branchName.FetchBranchListResponse
import com.pmb.domain.repository.auth.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchBranchListUseCase @Inject constructor(
    val authRepository: AuthRepository
) : BaseUseCase<FetchBranchListParams, FetchBranchListResponse>() {
    override suspend fun execute(params: FetchBranchListParams): Flow<Result<FetchBranchListResponse>> {
        return authRepository.fetchBranchList(
            stateCode = params.cityCode,
            cityCode = params.cityCode,
        )
    }
}

data class FetchBranchListParams(
     val stateCode: Int, val cityCode: Int
)