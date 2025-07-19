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
            mergeStatus = params.mergeStatus,
            stateCode = params.cityCode,
            cityCode = params.cityCode,
            organizationType = params.organizationType
        )
    }
}

data class FetchBranchListParams(
    val mergeStatus: Int, val stateCode: Int, val cityCode: Int, val organizationType: String
)