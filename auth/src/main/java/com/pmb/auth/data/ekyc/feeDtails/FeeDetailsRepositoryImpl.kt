package com.pmb.auth.data.ekyc.feeDtails

import com.pmb.auth.domain.ekyc.feeDetails.entity.FeeDetailsEntity
import com.pmb.auth.domain.ekyc.feeDetails.entity.FeeDetailsObject
import com.pmb.auth.domain.ekyc.feeDetails.repository.FeeDetailsRepository
import com.pmb.core.platform.Result
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FeeDetailsRepositoryImpl @Inject constructor() : FeeDetailsRepository {
    override suspend fun getFeeDetails(): Flow<Result<FeeDetailsEntity>> =
        flow {
            emit(Result.Loading)
            delay(2000)
            emit(
                Result.Success(
                    FeeDetailsEntity(
                        isSuccess = true, listOf(
                            FeeDetailsObject(
                                title = "فعالسازی اینترنت بانک",
                                price = 20000.0
                            ),
                            FeeDetailsObject(
                                title = "دریافت کارت بانکی",
                                price = 30000.0
                            ),
                            FeeDetailsObject(
                                title = "فعالسازی همراه بانک",
                                price = 20000.0
                            ),
                        ), 70000.0
                    )
                )
            )
        }

}