package com.pmb.auth.domain.register.search_opening_branch.useCase

import com.pmb.auth.domain.register.search_opening_branch.entity.OpeningBranchEntity
import com.pmb.auth.domain.register.search_opening_branch.entity.OpeningBranchParams
import com.pmb.auth.domain.register.search_opening_branch.repository.OpeningBranchRepository
import com.pmb.core.platform.BaseUseCase
import com.pmb.core.platform.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OpeningBranchUseCase @Inject constructor(
    private val openingBranchRepository: OpeningBranchRepository
) : BaseUseCase<OpeningBranchParams, OpeningBranchEntity>() {
    override suspend fun execute(params: OpeningBranchParams): Flow<Result<OpeningBranchEntity>> =
        openingBranchRepository.getOpeningBranch(params)
}