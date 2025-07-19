package com.pmb.auth.data.activation.tax_details

import com.pmb.auth.domain.activation.tax_details.repository.ActivationTaxDetailsRepository
import com.pmb.core.platform.Result
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ActivationTaxDetailsRepositoryImpl @Inject constructor() : ActivationTaxDetailsRepository {
    override suspend fun sendActivationTaxDetailsData(accountNumber: String): Flow<Result<Boolean>> =
        flow {
            emit(Result.Loading)
            emit(Result.Success(true))
        }
}