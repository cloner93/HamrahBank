package com.pmb.auth.domain.ekyc.feeDetails.useCase

import com.pmb.auth.domain.ekyc.feeDetails.entity.FeeDetailsEntity
import com.pmb.auth.domain.ekyc.feeDetails.repository.FeeDetailsRepository
import com.pmb.core.platform.BaseUseCase
import com.pmb.core.platform.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFeeDetailsUseCase @Inject constructor(
    private val feeDetailsRepository: FeeDetailsRepository
):BaseUseCase<Unit,FeeDetailsEntity>() {
    override suspend fun execute(params: Unit): Flow<Result<FeeDetailsEntity>> {
        return feeDetailsRepository.getFeeDetails()
    }
}