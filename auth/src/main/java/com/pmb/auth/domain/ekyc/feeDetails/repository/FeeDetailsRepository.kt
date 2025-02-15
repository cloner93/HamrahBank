package com.pmb.auth.domain.ekyc.feeDetails.repository

import com.pmb.auth.domain.ekyc.feeDetails.entity.FeeDetailsEntity
import com.pmb.core.platform.Result
import kotlinx.coroutines.flow.Flow

interface FeeDetailsRepository {
    suspend fun getFeeDetails(): Flow<Result<FeeDetailsEntity>>
}