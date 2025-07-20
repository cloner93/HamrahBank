package com.pmb.domain.usecae.auth.openAccount

import com.pmb.core.platform.BaseUseCase
import com.pmb.core.platform.Result
import com.pmb.domain.model.openAccount.branchName.Branch
import com.pmb.domain.repository.auth.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchBranchListUseCase @Inject constructor(
    val authRepository: AuthRepository
) : BaseUseCase<FetchBranchListParams, List<Branch>>() {
    override suspend fun execute(params: FetchBranchListParams): Flow<Result<List<Branch>>> {
        return authRepository.fetchBranchList(
            stateCode = params.stateCode,
            cityCode = params.cityCode,
        )
    }
}

data class FetchBranchListParams(
     val stateCode: Int, val cityCode: Int
)