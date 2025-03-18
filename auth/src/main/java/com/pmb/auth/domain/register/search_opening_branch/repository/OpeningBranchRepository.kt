package com.pmb.auth.domain.register.search_opening_branch.repository

import com.pmb.auth.domain.register.search_opening_branch.entity.OpeningBranchEntity
import com.pmb.auth.domain.register.search_opening_branch.entity.OpeningBranchParams
import com.pmb.core.platform.Result
import kotlinx.coroutines.flow.Flow

interface OpeningBranchRepository {
    suspend fun getOpeningBranch(params: OpeningBranchParams): Flow<Result<OpeningBranchEntity>>
}