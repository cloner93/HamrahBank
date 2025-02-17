package com.pmb.auth.domain.ekyc.fee_details.repository

import com.pmb.auth.domain.ekyc.fee_details.entity.FeeDetailsEntity
import com.pmb.core.platform.Result
import kotlinx.coroutines.flow.Flow

interface FeeDetailsRepository {
    suspend fun getFeeDetails(): Flow<Result<FeeDetailsEntity>>
}